package com.hassanmohammed.souhoolaapp.data.datasource

import com.hassanmohammed.souhoolaapp.data.db.PhotoDao
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlikerPhotoLocaleDataSourceImpl @Inject constructor(private val photoDao: PhotoDao) :
    FlikerPhotoLocalDataSource {
    override fun getPhotoFromDB(id: String): Flow<Photo> {
        return photoDao.getPhoto(id)
    }
}