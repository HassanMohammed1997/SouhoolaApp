package com.hassanmohammed.souhoolaapp.data

import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow

interface FlikerRepository {
    fun getPhotos() : Flow<PagingData<Photo>>

    fun getPhotoFromDatabase(id: String) : Flow<Photo>
}