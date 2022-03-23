package com.hassanmohammed.souhoolaapp.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "photos")
data class Photo(
    @PrimaryKey(autoGenerate = true)
    val pk: Int = 0,
    @ColumnInfo(name = "photo_id")
    val id: String = "",
    val owner: String = "",
    val secret: String = "",
    val server: String = "",
    val farm: Int = 0,
    val title: String = "",
    val ispublic: Int = 0,
    val isfriend: Int = 0,
    val isfamily: Int = 0
)