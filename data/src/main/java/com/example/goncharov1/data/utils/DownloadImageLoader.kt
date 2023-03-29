package com.example.goncharov1.data.utils

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager

interface DownloadImageLoader {
    fun downloadImage(
        urlImage: String,
        imagePlaceholder: Int,
        requestManager: RequestManager
    ): RequestBuilder<Drawable>
}