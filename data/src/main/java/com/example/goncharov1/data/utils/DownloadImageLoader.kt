package com.example.goncharov1.data.utils

import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.Target

interface DownloadImageLoader {
    fun downloadImage(
        urlImage: String,
        imagePlaceholder: Int,
        target: Target<Drawable>
    ): Target<Drawable>
}
