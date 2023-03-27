package com.example.goncharov1.data.utils

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder

interface DownloadImageLoader {
    fun downloadImage(urlImage: String, imagePlaceholder: Int): RequestBuilder<Drawable>
}