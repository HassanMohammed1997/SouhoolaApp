package com.hassanmohammed.souhoolaapp.domain.usecases

import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow

interface GetFlikerPhotoFromApiUseCase {
    operator fun invoke(query: String) : Flow<PagingData<Photo>>
}