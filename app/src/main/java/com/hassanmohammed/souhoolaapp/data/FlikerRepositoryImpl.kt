package com.hassanmohammed.souhoolaapp.data

import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.data.datasource.FlikerPhotoLocalDataSource
import com.hassanmohammed.souhoolaapp.data.datasource.FlikerPhotoLocaleDataSourceImpl
import com.hassanmohammed.souhoolaapp.data.datasource.FlikerPhotoRemoteDataSource
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlikerRepositoryImpl @Inject constructor(
    private val localeDataSource: FlikerPhotoLocalDataSource,
    private val remoteDataSource: FlikerPhotoRemoteDataSource

) : FlikerRepository {
    override fun getPhotos(): Flow<PagingData<Photo>> {
        return remoteDataSource.getPhotos()
    }

    override fun getPhotoFromDatabase(id: String): Flow<Photo> {
        return localeDataSource.getPhotoFromDB(id)
    }
}