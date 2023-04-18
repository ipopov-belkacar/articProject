package com.example.goncharov1.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goncharov1.data.db.ArticDao
import com.example.goncharov1.data.mappers.ArticMapper
import com.example.goncharov1.domain.entity.ArticEntity
import com.example.goncharov1.domain.getarticlist.GetArticListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val articListUseCase: GetArticListUseCase,
    private val articDao: ArticDao,
    private val articMapper: ArticMapper
) : ViewModel() {

    private var _isLoadingData = false
    val isLoadingData get() = _isLoadingData

    private val _loadingListDataArtic = MutableLiveData<List<ArticEntity>>()
    val loadingListDataArtic get() = _loadingListDataArtic

    fun getArticList(page: Int) {
        viewModelScope.launch {
            _isLoadingData = true
            _loadingListDataArtic.postValue(articListUseCase.getArticList(page))
            _isLoadingData = false
        }
    }
}