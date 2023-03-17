package com.example.goncharov1.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goncharov1.domain.entity.ArticEntity
import com.example.goncharov1.domain.getArtic.GetArticUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val articUseCase: GetArticUseCase) :
    ViewModel() {

    private val _getArticLiveData = MutableLiveData<List<ArticEntity>>()
    val getArticLiveData: MutableLiveData<List<ArticEntity>> = _getArticLiveData

    fun getArtic() {
        viewModelScope.launch {
            _getArticLiveData.postValue(articUseCase.getArtic())
        }
    }
}