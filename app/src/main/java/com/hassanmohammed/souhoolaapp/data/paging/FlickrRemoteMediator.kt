package com.hassanmohammed.souhoolaapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hassanmohammed.souhoolaapp.data.db.AppDatabase
import com.hassanmohammed.souhoolaapp.data.remote.FlickrService
import com.hassanmohammed.souhoolaapp.domain.models.FlickrPhotoRemoteKeys
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val DEFAULT_PAGE = 1

@OptIn(ExperimentalPagingApi::class)
class FlikerRemoteMediator @Inject constructor(
    private val service: FlickrService,
    private val db: AppDatabase,
    private val query: String

) : RemoteMediator<Int, Photo>() {
    private val photoDao = db.photoDao()
    private val photoRemoteKeysDao = db.photoRemoteKeysDao()


    override suspend fun load(loadType: LoadType, state: PagingState<Int, Photo>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevKey ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextKey ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = service.getPhotos(page = page, text = query)
            var endOfPaginationReached = false
            if (response.isSuccessful) {
                val data = response.body()
                endOfPaginationReached = data == null
                data?.let {
                    db.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            photoDao.deleteAllPhotos()
                            photoRemoteKeysDao.deleteAllRemoteKeys()
                        }
                        val prevKey = if (page == DEFAULT_PAGE) null else page - 1
                        val nextKey = if (endOfPaginationReached) null else page + 1
                        val keys = data.photos.photo.map {
                            FlickrPhotoRemoteKeys(
                                id = it.id,
                                prevKey = prevKey,
                                nextKey = nextKey
                            )
                        }
                        db.photoRemoteKeysDao().addPhotoRemoteKeys(keys)
                        db.photoDao().insertAll(data.photos.photo)
                    }

                }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Photo>,
    ): FlickrPhotoRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                photoRemoteKeysDao.getPhotoRemoteKey(photoId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Photo>,
    ): FlickrPhotoRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { photo ->
                photoRemoteKeysDao.getPhotoRemoteKey(photoId = photo.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Photo>,
    ): FlickrPhotoRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { photo ->
                photoRemoteKeysDao.getPhotoRemoteKey(photoId = photo.id)
            }
    }

}