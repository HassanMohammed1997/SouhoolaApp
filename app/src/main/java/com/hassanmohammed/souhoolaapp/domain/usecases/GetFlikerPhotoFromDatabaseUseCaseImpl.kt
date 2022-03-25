package com.hassanmohammed.souhoolaapp.domain.usecases

import com.hassanmohammed.souhoolaapp.data.repository.FlickrRepository
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow

class GetFlikerPhotoFromDatabaseUseCaseImpl(private val repository: FlickrRepository): GetFlikerPhotoFromDatabaseUseCase {
    override fun invoke(photoId: String) : Flow<Photo> {
        return repository.getPhotoFromDatabase(id = photoId)
    }
}