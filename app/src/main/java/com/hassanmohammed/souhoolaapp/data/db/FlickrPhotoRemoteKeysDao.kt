package com.hassanmohammed.souhoolaapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hassanmohammed.souhoolaapp.domain.models.FlickrPhotoRemoteKeys

@Dao
interface FlickrPhotoRemoteKeysDao {
    @Query("SELECT * FROM photo_remote_keys WHERE id = :photoId")
    suspend fun getPhotoRemoteKey(photoId: String) : FlickrPhotoRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPhotoRemoteKeys(photoRemoteKeys: List<FlickrPhotoRemoteKeys>)

    @Query("DELETE FROM photo_remote_keys")
    suspend fun deleteAllRemoteKeys()

}