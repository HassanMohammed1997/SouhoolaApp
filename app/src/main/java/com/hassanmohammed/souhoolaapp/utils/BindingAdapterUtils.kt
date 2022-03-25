package com.hassanmohammed.souhoolaapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.hassanmohammed.souhoolaapp.R

object BindingAdapterUtils {
    @BindingAdapter("app:loadFlikerPhoto")
    @JvmStatic
    fun ImageView.loadFlikerPhoto(url: String) {
        this.load(url) {
            crossfade(true)
            placeholder(R.drawable.no_image_placeholder)
            error(R.drawable.error_placeholder_img)
            fallback(R.drawable.no_image_placeholder)

        }
    }

}