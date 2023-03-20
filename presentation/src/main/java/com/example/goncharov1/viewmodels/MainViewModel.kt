package com.example.goncharov1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.goncharov1.domain.getArtic.GetArticUseCase
import com.example.goncharov1.ui.recycler.paging.ArticPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val articUseCase: GetArticUseCase) :
    ViewModel() {

    val getArtic = Pager(PagingConfig(1)) {
        ArticPagingSource(articUseCase)
    }.flow.cachedIn(viewModelScope)
}