package com.hassanmohammed.souhoolaapp.data.datasource

import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow

interface FlikerPhotoRemoteDataSource {
    fun getPhotos() : Flow<PagingData<Photo>>
}