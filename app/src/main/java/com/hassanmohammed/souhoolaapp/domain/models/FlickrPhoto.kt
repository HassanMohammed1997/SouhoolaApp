package com.hassanmohammed.souhoolaapp.domain.models



data class FlickrPhoto(
    val photos: PhotoData = PhotoData(),
    val stat: String = ""
)