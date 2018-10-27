package com.taylorcase.hearthstonescry.utils

import android.support.v7.app.AppCompatActivity
import android.widget.ImageView

interface ImageLoader {

    fun loadImage(url: String, activity: AppCompatActivity, imageView: ImageView)

    fun loadDrawableCenterCrop(imageResId: Int, imageView: ImageView)

    fun loadDrawableCenterInside(imageResId: Int, imageView: ImageView)

    fun loadImage(url: String, imageView: ImageView)
}


