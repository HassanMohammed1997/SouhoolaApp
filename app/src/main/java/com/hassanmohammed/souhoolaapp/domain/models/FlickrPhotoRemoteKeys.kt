package com.hassanmohammed.souhoolaapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_remote_keys")
data class FlickrPhotoRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevKey: Int?,
    val nextKey: Int?,
) {
}