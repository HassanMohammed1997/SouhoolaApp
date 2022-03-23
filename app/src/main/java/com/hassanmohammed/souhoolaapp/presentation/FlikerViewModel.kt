package com.hassanmohammed.souhoolaapp.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.hassanmohammed.souhoolaapp.data.resporitory.FlikerRepository
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FlikerViewModel @Inject constructor(
    private val repository: FlikerRepository
) : ViewModel() {

    fun getPhotos(): Flow<PagingData<Photo>> {
        return repository.getPhotos()
    }
}