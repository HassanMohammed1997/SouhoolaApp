package com.hassanmohammed.souhoolaapp.domain.models


data class PhotoData(
    val page: Int = 0,
    val pages: Int = 0,
    val perpage: Int = 0,
    val total: Int = 0,
    val photo: List<Photo> = listOf()
)