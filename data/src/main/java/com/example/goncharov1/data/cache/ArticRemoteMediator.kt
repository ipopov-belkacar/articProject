package com.example.goncharov1.data.cache

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.goncharov1.data.db.ArticDao
import com.example.goncharov1.data.db.ArticRemoteKey
import com.example.goncharov1.data.mappers.ArticMapper
import com.example.goncharov1.data.network.RetrofitClient
import com.example.goncharov1.domain.entity.ArticEntity
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
                        getLastRemoteKey(state) ?: throw InvalidObjectException("Last key empty")
                    remoteKey.next ?: return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.REFRESH -> {
                    val remoteKey = getClosestRemoteKey(state)
                    remoteKey?.next?.minus(1) ?: initialPage
                }
            }

            val retrofit = RetrofitClient.create()
            val callGetArtic = retrofit.getArtic(page)

            if (callGetArtic.isSuccessful) {

                val articEntityList = callGetArtic.body()?.let { articMapper.toDomain(it) }
                val endOfPagination = articEntityList?.size!! < state.config.pageSize

                val prev = if (page == initialPage) null else -1
                val next = if (endOfPagination) null else page + 1

                if (loadType == LoadType.REFRESH) {
                    articDao.deleteAllArtic()
                    articDao.deleteAllArticRemoteKey()
                }

                val list = articEntityList.map {
                    ArticRemoteKey(id = it.id, prev, next)
                }

                if (list != null) {
                    articDao.insertAllRemoteKey(list)
                }

                articDao.insertArticListEntity(articEntityList)

                MediatorResult.Success(endOfPagination)
            } else {
                MediatorResult.Success(endOfPaginationReached = true)
            }

        } catch (e: Exception) {
            Log.e("Error remote mediator", e.toString())
            MediatorResult.Error(e)
        }
    }

    private suspend fun getClosestRemoteKey(state: PagingState<Int, ArticEntity>): ArticRemoteKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                articDao.getAllRemoteKey(it.id)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, ArticEntity>): ArticRemoteKey? {
        return state.lastItemOrNull()?.let {
            articDao.getAllRemoteKey(it.id)
        }
    }
}