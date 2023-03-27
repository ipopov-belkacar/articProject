package com.example.goncharov1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.goncharov1.data.cache.ArticRemoteMediator
import com.example.goncharov1.data.db.ArticDao
import com.example.goncharov1.data.mappers.ArticMapper
import com.example.goncharov1.domain.getArtic.GetArticUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val articUseCase: GetArticUseCase,
    private val articDao: ArticDao,
    private val articMapper: ArticMapper
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val articListFlow =
        Pager(PagingConfig(12), remoteMediator = ArticRemoteMediator(articDao, 1, articMapper)) {
            articDao.getAllArtic()
        }.flow
}