package com.example.goncharov1.viewmodels

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.goncharov1.data.db.ArticDao
import com.example.goncharov1.data.mappers.ArticMapper
import com.example.goncharov1.data.paging.ArticRemoteMediator
import com.example.goncharov1.domain.entity.ArticEntity
import com.example.goncharov1.domain.getarticlist.GetArticListUseCase
import com.example.goncharov1.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val articListUseCase: GetArticListUseCase,
    private val articDao: ArticDao,
    private val articMapper: ArticMapper
) : BaseViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    fun articListFlow(): Flow<PagingData<ArticEntity>> {
        return Pager(
            PagingConfig(12),
            remoteMediator = ArticRemoteMediator(articDao, articMapper)
        ) {
            articDao.getAllArtic()
        }.flow
    }
}