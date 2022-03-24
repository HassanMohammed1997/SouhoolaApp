package com.hassanmohammed.souhoolaapp.domain.usecases

import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow

interface GetFlikerPhotoFromDatabaseUseCase {
    operator fun invoke(photoId: String) : Flow<Photo>
}