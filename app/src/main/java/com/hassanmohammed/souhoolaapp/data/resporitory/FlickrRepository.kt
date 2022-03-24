package com.hassanmohammed.souhoolaapp.data.resporitory

import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow

interface FlickrRepository {
    fun getPhotos() : Flow<PagingData<Photo>>

    fun getPhotoFromDatabase(id: String) : Flow<Photo>
}