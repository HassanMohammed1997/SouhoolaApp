package com.hassanmohammed.souhoolaapp.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.data.db.PhotoDatabase
import com.hassanmohammed.souhoolaapp.data.paging.FlikerRemoteMediator
import com.hassanmohammed.souhoolaapp.data.remote.FlikerService
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlikerPhotoRemoteDataSourceImpl @Inject constructor(
    private val api: FlikerService,
    private val db: PhotoDatabase
) : FlikerPhotoRemoteDataSource {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPhotos(): Flow<PagingData<Photo>> {
        val pagingSourceFactory = { db.photoDao().getAllPhotos() }
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = FlikerRemoteMediator(
                api,
                db
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}