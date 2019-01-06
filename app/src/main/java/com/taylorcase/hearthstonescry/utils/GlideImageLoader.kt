package com.taylorcase.hearthstonescry.utils

import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.taylorcase.hearthstonescry.R
import javax.inject.Inject
import javax.inject.Singleton

open class GlideImageLoader : ImageLoader {

    // TODO: Lint warning for casting -- fix this
    override fun loadImage(url: String, activity: AppCompatActivity, imageView: ImageView) {
        val requestOptions = RequestOptions().placeholder(R.drawable.card_back_default).dontAnimate()
        Glide.with(activity).load(url).apply(requestOptions).listener(activity as RequestListener<Drawable>).into(imageView)
    }

    override fun loadImage(url: String, imageView: ImageView) {
        val requestOptions = RequestOptions().placeholder(R.drawable.card_back_default)
        Glide.with(imageView.context).load(url).apply(requestOptions).into(imageView)
    }

    override fun loadDrawableCenterCrop(imageResId: Int, imageView: ImageView) {
        val requestOptions = RequestOptions().centerCrop()
        Glide.with(imageView.context).load(imageResId).apply(requestOptions).into(imageView)
    }

    override fun loadDrawableCenterInside(imageResId: Int, imageView: ImageView) {
        val requestOptions = RequestOptions().centerInside()
        Glide.with(imageView.context).load(imageResId).apply(requestOptions).into(imageView)
    }

}
