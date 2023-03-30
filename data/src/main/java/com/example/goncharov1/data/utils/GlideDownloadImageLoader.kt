package com.example.goncharov1.data.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ImageViewTarget
import javax.inject.Inject
import com.bumptech.glide.request.target.Target

private const val STANDARD_IMAGE_WIDTH = 600
private const val STANDARD_IMAGE_HEIGHT = 600

class GlideDownloadImageLoader @Inject constructor(val context: Context) : DownloadImageLoader {

    override fun downloadImage(
        urlImage: String,
        imagePlaceholder: Int,
        target: Target<Drawable>
    ): Target<Drawable> {
        return Glide.with(context)
            .load(urlImage)
            .override(STANDARD_IMAGE_WIDTH, STANDARD_IMAGE_HEIGHT)
            .centerCrop()
            .placeholder(imagePlaceholder)
            .into(target)
    }

    class GlideTarget(imageView: ImageView): ImageViewTarget<Drawable>(imageView) {
        override fun setResource(resource: Drawable?) {
            view.setImageDrawable(resource)
        }
    }
}
