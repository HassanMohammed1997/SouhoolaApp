package com.hassanmohammed.souhoolaapp.data.repository

import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow

interface FlickrRepository {
    fun getPhotos(query: String) : Flow<PagingData<Photo>>

    fun getPhotoFromDatabase(id: String) : Flow<Photo>
}