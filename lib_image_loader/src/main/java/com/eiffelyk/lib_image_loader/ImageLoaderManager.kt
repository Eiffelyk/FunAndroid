package com.eiffelyk.lib_image_loader

import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoaderManager private constructor() {
    private object Singleton {
        val holder = ImageLoaderManager()
    }

    companion object {
        val instance: ImageLoaderManager = Singleton.holder
    }

    fun displayImageForView(image: ImageView, url: String) {
        Glide.with(image.context).asBitmap().load(url).into(image)
    }
}