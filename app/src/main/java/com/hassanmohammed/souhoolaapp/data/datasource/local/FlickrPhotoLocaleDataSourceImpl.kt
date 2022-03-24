package com.hassanmohammed.souhoolaapp.data.datasource.local

import com.hassanmohammed.souhoolaapp.data.db.FlickrPhotoDao
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlickrPhotoLocaleDataSourceImpl @Inject constructor(private val flickrPhotoDao: FlickrPhotoDao) :
    FlickrPhotoLocalDataSource {
    override fun getPhotoFromDB(id: String): Flow<Photo> {
        return flickrPhotoDao.getPhoto(id)
    }
}