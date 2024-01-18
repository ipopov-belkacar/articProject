package com.example.goncharov1.viewmodels

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.RequestBuilder
import com.example.goncharov1.data.utils.DownloadImageLoader
import com.example.goncharov1.domain.getartic.GetArticUseCase
import com.example.goncharov1.ui.base.BaseViewModel
import com.example.goncharov1.ui.fragment.detail.DetailFragmentArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val downloadImageLoader: DownloadImageLoader,
    private val getArticUseCase: GetArticUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()

    fun getArticById() {
        viewModelScope.launch {
            try {
                val articId = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle).paramArticId
                val articEntity = getArticUseCase.getArtic(articId)

                eventChannel.send(
                    Event.AttachViewToWindow(
                        description = articEntity.artistDisplay,
                        title = articEntity.title,
                        imageId = articEntity.imageId
                    )
                )
                eventChannel.send(
                    Event.DownloadImageFromNetwork(
                        imageId = articEntity.imageId
                    )
                )

            } catch (e: java.lang.IllegalArgumentException) {
                Log.e(javaClass.simpleName, e.message.toString())
            }
        }
    }

    fun downloadImage(urlImage: String, imagePlaceholder: Int) {
        val downloadImage = downloadImageLoader.downloadImage(
            urlImage, imagePlaceholder
        )

        viewModelScope.launch {
            eventChannel.send(
                Event.AttachImageToWindow(
                    downloadImage
                )
            )
        }
    }
}

sealed class Event {
    data class AttachViewToWindow(
        val description: String,
        val title: String,
        val imageId: String?
    ) : Event()

    data class AttachImageToWindow(val requestBuilder: RequestBuilder<Drawable>) : Event()

    data class DownloadImageFromNetwork(val imageId: String?) : Event()
}