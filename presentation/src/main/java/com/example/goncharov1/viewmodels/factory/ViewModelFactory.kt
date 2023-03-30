package com.example.goncharov1.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goncharov1.data.db.ArticDao
import com.example.goncharov1.data.mappers.ArticMapper
import com.example.goncharov1.data.utils.DownloadImageLoader
import com.example.goncharov1.domain.getArtic.GetArticUseCaseImpl
import com.example.goncharov1.viewmodels.DetailViewModel
import com.example.goncharov1.viewmodels.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val articDao: ArticDao,
    private val articMapper: ArticMapper,
    private val articUseCaseImpl: GetArticUseCaseImpl,
    private val downloadImageLoader: DownloadImageLoader
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.articUseCaseImpl, this.articDao, this.articMapper) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            DetailViewModel(this.downloadImageLoader) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}