package com.hassanmohammed.souhoolaapp.data.datasource.remote

import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow

interface FlickrPhotoRemoteDataSource {
    fun getPhotos() : Flow<PagingData<Photo>>
}