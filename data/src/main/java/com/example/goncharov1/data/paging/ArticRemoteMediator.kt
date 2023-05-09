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

    private val retrofit = RetrofitClient.create()
    private var pageIndex = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticEntity>
    ): MediatorResult {

        pageIndex = getPageIndex(loadType) ?:
            return MediatorResult.Success(endOfPaginationReached = true)

        return try {
            val response = getRemoteArtic(pageIndex)

            if (response.isSuccessful) {
                val articEntityList = response.body()?.let { articMapper.toDomain(it) }
                val endOfPagination = articEntityList?.size!! < state.config.pageSize

                if (loadType == LoadType.REFRESH) {
                    articDao.refresh(articEntityList)
                } else {
                    articDao.insertArticListEntity(articEntityList)
                }
                MediatorResult.Success(endOfPagination)
            } else {
                MediatorResult.Success(endOfPaginationReached = true)
            }

        } catch (e: Exception) {
            Log.e("Error remote mediator", e.toString())
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }

    private suspend fun getRemoteArtic(page: Int): Response<ArticModel> {
        return retrofit.getArtic(page)
    }
}