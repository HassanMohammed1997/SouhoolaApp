package com.hassanmohammed.souhoolaapp.data.datasource.local

import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow

interface FlikerPhotoLocalDataSource {
    fun getPhotoFromDB(id: String) : Flow<Photo>
}