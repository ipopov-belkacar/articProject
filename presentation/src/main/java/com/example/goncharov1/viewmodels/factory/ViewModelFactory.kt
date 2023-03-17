package com.example.goncharov1.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goncharov1.domain.getArtic.GetArticUseCaseImpl
import com.example.goncharov1.viewmodels.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val articUseCaseImpl: GetArticUseCaseImpl) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.articUseCaseImpl) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}