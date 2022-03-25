package com.hassanmohammed.souhoolaapp.domain.usecases

import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.data.repository.FlickrRepository
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow

class GetFlikerPhotoFromApiUseCaseImpl(private val repository: FlickrRepository) : GetFlikerPhotoFromApiUseCase {
    override fun invoke(query: String): Flow<PagingData<Photo>> {
        return repository.getPhotos(query)
    }
}