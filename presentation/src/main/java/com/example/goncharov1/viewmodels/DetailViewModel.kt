package com.example.goncharov1.viewmodels

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.RequestBuilder
import com.example.goncharov1.data.utils.DownloadImageLoader
import com.example.goncharov1.domain.entity.ArticEntity
import com.example.goncharov1.domain.getartic.GetArticUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val downloadImageLoader: DownloadImageLoader,
    private val getArticUseCase: GetArticUseCase
) : ViewModel() {

    private val _downloadImageLiveData = MutableLiveData<RequestBuilder<Drawable>>()
    val downloadImageLiveData: MutableLiveData<RequestBuilder<Drawable>> get() = _downloadImageLiveData

    private val _getArticEntityLiveData = MutableLiveData<ArticEntity>()
    val getArticEntityLiveData: MutableLiveData<ArticEntity> get() = _getArticEntityLiveData

    fun getArticById(id: Int) {
        try {
            viewModelScope.launch {
                _getArticEntityLiveData.postValue(getArticUseCase.getArtic(id))
            }
        } catch (e: java.lang.IllegalArgumentException) {
            Log.e(javaClass.simpleName, e.message.toString())
        }
    }

    fun downloadImage(urlImage: String, imagePlaceholder: Int) {
        _downloadImageLiveData.postValue(
            downloadImageLoader.downloadImage(
                urlImage,
                imagePlaceholder
            )
        )
    }
}