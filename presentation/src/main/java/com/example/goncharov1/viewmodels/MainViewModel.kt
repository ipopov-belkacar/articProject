package com.example.goncharov1.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goncharov1.domain.entity.ArticEntity
import com.example.goncharov1.domain.getArtic.GetArticUseCaseImpl
import javax.inject.Inject

class MainViewModel @Inject constructor(private val articUseCaseImpl: GetArticUseCaseImpl) :
    ViewModel() {

    var getArticLiveData = MutableLiveData<List<ArticEntity>>()

    suspend fun getArtic() {
        getArticLiveData.postValue(articUseCaseImpl.mainRepository.getArtic())
    }
}