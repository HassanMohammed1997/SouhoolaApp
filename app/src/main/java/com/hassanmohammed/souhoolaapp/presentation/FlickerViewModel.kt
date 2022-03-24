package com.hassanmohammed.souhoolaapp.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import com.hassanmohammed.souhoolaapp.domain.usecases.GetFlikerPhotoFromApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FlickerViewModel @Inject constructor(
    private val getFlikerPhotoFromApi: GetFlikerPhotoFromApiUseCase
) : ViewModel() {

    fun getPhotos(): Flow<PagingData<Photo>> {
        return getFlikerPhotoFromApi()
    }
}