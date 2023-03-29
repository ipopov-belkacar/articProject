package com.example.goncharov1.data.utils

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import javax.inject.Inject

private const val STANDARD_IMAGE_WIDTH = 600
private const val STANDARD_IMAGE_HEIGHT = 600

class DownloadImageLoaderImpl @Inject constructor(
    private val requestManager: RequestManager
) : DownloadImageLoader {
    override fun downloadImage(
        urlImage: String,
        imagePlaceholder: Int,
    ): RequestBuilder<Drawable> {
        return requestManager
            .load(urlImage)
            .override(STANDARD_IMAGE_WIDTH, STANDARD_IMAGE_HEIGHT)
            .centerCrop()
            .placeholder(imagePlaceholder)
    }
}