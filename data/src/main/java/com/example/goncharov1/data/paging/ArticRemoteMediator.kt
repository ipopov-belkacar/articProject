package com.example.goncharov1.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.goncharov1.data.db.ArticDao
import com.example.goncharov1.data.mappers.ArticMapper
import com.example.goncharov1.data.model.ArticModel
import com.example.goncharov1.data.network.RetrofitClient
import com.example.goncharov1.domain.entity.ArticEntity
import retrofit2.Response
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ArticRemoteMediator @Inject constructor(
    private val articDao: ArticDao,
    private var articMapper: ArticMapper
) : RemoteMediator<Int, ArticEntity>() {

    private var nextPage = 1
    private val retrofit = RetrofitClient.create()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticEntity>
    ): MediatorResult {

        return try {
            val page = when (loadType) {
                LoadType.APPEND -> {
                    nextPage
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.REFRESH -> {
                    nextPage
                }
            }

            val response = getRemoteArtic(page)

            if (response.isSuccessful) {

                val articEntityList = response.body()?.let { articMapper.toDomain(it) }
                articDao.insertArticListEntity(articEntityList)
                nextPage++

                MediatorResult.Success(false)
            } else {
                MediatorResult.Success(endOfPaginationReached = true)
            }

        } catch (e: Exception) {
            Log.e("Error remote mediator", e.toString())
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteArtic(page: Int): Response<ArticModel> {
        return retrofit.getArtic(page)
    }
}