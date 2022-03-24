package com.hassanmohammed.souhoolaapp.data.resporitory

import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.data.datasource.local.FlickrPhotoLocalDataSource
import com.hassanmohammed.souhoolaapp.data.datasource.remote.FlickrPhotoRemoteDataSource
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlickrRepositoryImpl @Inject constructor(
    private val localeDataSource: FlickrPhotoLocalDataSource,
    private val remoteDataSource: FlickrPhotoRemoteDataSource

) : FlickrRepository {
    override fun getPhotos(): Flow<PagingData<Photo>> {
        return remoteDataSource.getPhotos()
    }

    override fun getPhotoFromDatabase(id: String): Flow<Photo> {
        return localeDataSource.getPhotoFromDB(id)
    }
}