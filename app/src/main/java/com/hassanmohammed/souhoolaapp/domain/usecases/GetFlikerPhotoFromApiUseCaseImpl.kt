package com.hassanmohammed.souhoolaapp.domain.usecases

import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.data.resporitory.FlikerRepository
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import kotlinx.coroutines.flow.Flow

class GetFlikerPhotoFromApiUseCaseImpl(private val repository: FlikerRepository) : GetFlikerPhotoFromApiUseCase {
    override fun invoke(): Flow<PagingData<Photo>> {
        return repository.getPhotos()
    }
}