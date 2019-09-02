package com.adnagu.kotlintutorial.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

/**
 * Created on 8/16/2019
 * @author wmramazan
 */
@BindingAdapter("isNetworkError", "isSuccessfulRefresh")
fun hideProgress(view: View, isNetworkError: Boolean, isSuccessfulRefresh: Boolean) {
    Timber.d(isNetworkError.toString())
    if (isNetworkError || isSuccessfulRefresh) {
        view.visibility = View.GONE
    }
}

@BindingAdapter("url")
fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}