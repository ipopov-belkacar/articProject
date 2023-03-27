package com.example.goncharov1.data.utils

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import javax.inject.Inject

private const val STANDARD_IMAGE_WIDTH = 600
private const val STANDARD_IMAGE_HEIGHT = 600

class DownloadImageLoaderImpl @Inject constructor(val context: Context) : DownloadImageLoader {
    override fun downloadImage(
        urlImage: String,
        imagePlaceholder: Int
    ): RequestBuilder<Drawable> {
        return Glide.with(context)
            .load(urlImage)
            .override(STANDARD_IMAGE_WIDTH, STANDARD_IMAGE_HEIGHT)
            .centerCrop()
            .placeholder(imagePlaceholder)
    }
}