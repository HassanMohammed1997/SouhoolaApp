package com.hassanmohammed.souhoolaapp.data.datasource.local

import com.hassanmohammed.souhoolaapp.data.db.FlikerPhotoDao
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlikerPhotoLocaleDataSourceImpl @Inject constructor(private val flikerPhotoDao: FlikerPhotoDao) :
    FlikerPhotoLocalDataSource {
    override fun getPhotoFromDB(id: String): Flow<Photo> {
        return flikerPhotoDao.getPhoto(id)
    }
}