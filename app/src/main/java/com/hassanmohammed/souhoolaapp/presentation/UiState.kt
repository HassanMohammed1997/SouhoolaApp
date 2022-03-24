package com.hassanmohammed.souhoolaapp.presentation

import com.hassanmohammed.souhoolaapp.domain.models.Photo

sealed class UiModel {
    data class PhotoItem(val photo: Photo) : UiModel()
    object BannerItem : UiModel()
}