package com.hassanmohammed.souhoolaapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hassanmohammed.souhoolaapp.domain.models.FlikerPhotoRemoteKeys
import com.hassanmohammed.souhoolaapp.domain.models.Photo

@Database(
    entities = [Photo::class, FlikerPhotoRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class PhotoDatabase: RoomDatabase() {
    abstract fun photoDao() : PhotoDao
    abstract fun photoRemoteKeysDao() : PhotoRemoteKeysDao
}