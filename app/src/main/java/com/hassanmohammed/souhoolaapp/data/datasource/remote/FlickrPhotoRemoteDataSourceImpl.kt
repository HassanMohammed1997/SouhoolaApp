package com.hassanmohammed.souhoolaapp.data.datasource.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.data.db.AppDatabase
import com.hassanmohammed.souhoolaapp.data.paging.FlikerRemoteMediator
import com.hassanmohammed.souhoolaapp.data.remote.FlickrService
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlickrPhotoRemoteDataSourceImpl @Inject constructor(
    private val api: FlickrService,
    private val db: AppDatabase,
) : FlickrPhotoRemoteDataSource {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPhotos(query: String): Flow<PagingData<Photo>> {
        val pagingSourceFactory = { db.photoDao().getAllPhotos() }
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = FlikerRemoteMediator(
                api,
                db,
                query
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}