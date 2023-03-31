package com.example.goncharov1.viewmodels

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.RequestBuilder
import com.example.goncharov1.data.utils.DownloadImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val downloadImageLoader: DownloadImageLoader) :
    ViewModel() {

    private val _downloadImageLiveData = MutableLiveData<RequestBuilder<Drawable>>()
    val downloadImageLiveData: MutableLiveData<RequestBuilder<Drawable>> get() = _downloadImageLiveData

    fun downloadImage(urlImage: String, imagePlaceholder: Int) {
        _downloadImageLiveData.postValue(
            downloadImageLoader.downloadImage(
                urlImage,
                imagePlaceholder
            )
        )
    }
}