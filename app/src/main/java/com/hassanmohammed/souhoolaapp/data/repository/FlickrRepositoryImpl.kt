package com.hassanmohammed.souhoolaapp.data.repository

import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.data.datasource.local.FlickrPhotoLocalDataSource
import com.hassanmohammed.souhoolaapp.data.datasource.remote.FlickrPhotoRemoteDataSource
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlickrRepositoryImpl @Inject constructor(
    private val localDataSource: FlickrPhotoLocalDataSource,
    private val remoteDataSource: FlickrPhotoRemoteDataSource

) : FlickrRepository {
    override fun getPhotos(query:String): Flow<PagingData<Photo>> {
        return remoteDataSource.getPhotos(query)
    }

    override fun getPhotoFromDatabase(id: String): Flow<Photo> {
        return localDataSource.getPhotoFromDB(id)
    }
}