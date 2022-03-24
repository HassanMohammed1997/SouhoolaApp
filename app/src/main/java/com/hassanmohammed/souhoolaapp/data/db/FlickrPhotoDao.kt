package com.hassanmohammed.souhoolaapp.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface FlickrPhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<Photo>)

    @Query("SELECT * FROM photos")
    fun getAllPhotos(): PagingSource<Int, Photo>

    @Query("SELECT * FROM photos WHERE photo_id = :photoId")
    fun getPhoto(photoId: String): Flow<Photo>

    @Query("DELETE FROM photos")
    suspend fun deleteAllPhotos()
}