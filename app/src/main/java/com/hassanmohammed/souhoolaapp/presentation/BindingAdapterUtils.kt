package com.hassanmohammed.souhoolaapp.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.hassanmohammed.souhoolaapp.R
import com.hassanmohammed.souhoolaapp.domain.models.Photo

object BindingAdapterUtils {
    @BindingAdapter("app:loadFlikerPhoto")
    @JvmStatic
    fun ImageView.loadFlikerPhoto(url: String){
        this.load(url){
            crossfade(true)
            placeholder(R.drawable.no_image_placeholder)
        }
    }

}