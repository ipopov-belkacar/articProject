package com.example.goncharov1.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.goncharov1.data.db.ArticDao
import com.example.goncharov1.data.db.ArticRemoteKey
import com.example.goncharov1.data.mappers.ArticMapper
import com.example.goncharov1.data.model.ArticModel
import com.example.goncharov1.data.network.RetrofitClient
import com.example.goncharov1.domain.entity.ArticEntity
import retrofit2.Response
import java.io.InvalidObjectException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ArticRemoteMediator @Inject constructor(
    private val articDao: ArticDao,
    private val initialPage: Int = 1,
    private var articMapper: ArticMapper
) : RemoteMediator<Int, ArticEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticEntity>
    ): MediatorResult {

        return try {
            val page = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKey =
                        articDao.getMaxRemoteKey()
                            ?: throw InvalidObjectException("Last key empty")
                    remoteKey.next ?: return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.REFRESH -> {
                    initialPage
                }
            }

            val response = getRemoteArtic(page)

            if (response.isSuccessful) {

                val articEntityList = response.body()?.let { articMapper.toDomain(it) }
                val endOfPagination = articEntityList?.size!! < state.config.pageSize

                val prev = if (page == initialPage) null else page - 1
                val next = if (endOfPagination) null else page + 1

                val listRemoteKey = articEntityList.map {
                    ArticRemoteKey(id = it.id, prev, next)
                }

                clearDataInDb(loadType)
                insertRemoteKeyAndEntityArtic(listRemoteKey, articEntityList)

                MediatorResult.Success(endOfPagination)
            } else {
                MediatorResult.Success(endOfPaginationReached = true)
            }

        } catch (e: Exception) {
            Log.e("Error remote mediator", e.toString())
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteArtic(page: Int): Response<ArticModel> {
        val retrofit = RetrofitClient.create()
        return retrofit.getArtic(page)
    }

    private suspend fun clearDataInDb(loadType: LoadType) {
        if (loadType == LoadType.REFRESH) {
            articDao.deleteAllArtic()
            articDao.deleteAllArticRemoteKey()
        }
    }

    private suspend fun insertRemoteKeyAndEntityArtic(
        listRemoteKey: List<ArticRemoteKey>,
        articEntity: List<ArticEntity>
    ) {
        articDao.insertAllRemoteKey(listRemoteKey)
        articDao.insertArticListEntity(articEntity)
    }

    private suspend fun getClosestRemoteKey(state: PagingState<Int, ArticEntity>): ArticRemoteKey? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestItemToPosition(anchorPosition)?.let { articEntity ->
                articDao.getAllRemoteKeysById(articEntity.id)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, ArticEntity>): ArticRemoteKey? {
        return state.lastItemOrNull()?.let {
            articDao.getAllRemoteKeysById(it.id)
        }
    }
}