package com.hassanmohammed.souhoolaapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hassanmohammed.souhoolaapp.domain.models.FlickrPhotoRemoteKeys
import com.hassanmohammed.souhoolaapp.domain.models.Photo

@Database(
    entities = [Photo::class, FlickrPhotoRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun photoDao() : FlickrPhotoDao
    abstract fun photoRemoteKeysDao() : FlickrPhotoRemoteKeysDao
}