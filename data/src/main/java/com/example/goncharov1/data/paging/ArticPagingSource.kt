package com.example.goncharov1.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.goncharov1.domain.entity.ArticEntity
import com.example.goncharov1.domain.getArtic.GetArticUseCase

@Deprecated("Not currently in use")
class ArticPagingSource(private val getArticUseCase: GetArticUseCase) :
    PagingSource<Int, ArticEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticEntity> {
        return try {
            val currentPage = params.key ?: 1
            val response = getArticUseCase.getArtic(currentPage)

            LoadResult.Page(
                data = response,
                nextKey = currentPage.plus(1),
                prevKey = if (currentPage == 1) null else currentPage - 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticEntity>): Int? {
        return null
    }
}