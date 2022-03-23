package com.hassanmohammed.souhoolaapp.di

import com.hassanmohammed.souhoolaapp.data.FlikerRepository
import com.hassanmohammed.souhoolaapp.data.FlikerRepositoryImpl
import com.hassanmohammed.souhoolaapp.data.datasource.FlikerPhotoLocalDataSource
import com.hassanmohammed.souhoolaapp.data.datasource.FlikerPhotoLocaleDataSourceImpl
import com.hassanmohammed.souhoolaapp.data.datasource.FlikerPhotoRemoteDataSource
import com.hassanmohammed.souhoolaapp.data.datasource.FlikerPhotoRemoteDataSourceImpl
import com.hassanmohammed.souhoolaapp.data.db.PhotoDao
import com.hassanmohammed.souhoolaapp.data.db.PhotoDatabase
import com.hassanmohammed.souhoolaapp.data.remote.FlikerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideFlikerPhotoLocaleDataSource(photoDao: PhotoDao): FlikerPhotoLocalDataSource {
        return FlikerPhotoLocaleDataSourceImpl(photoDao)
    }

    @Provides
    @Singleton
    fun provideFlikerPhotoRemoteLocalDataSource(
        api: FlikerService,
        db: PhotoDatabase
    ): FlikerPhotoRemoteDataSource {
        return FlikerPhotoRemoteDataSourceImpl(api, db)
    }

    @Provides
    @Singleton
    fun provideFlikerRepository(
        localDataSource: FlikerPhotoLocalDataSource,
        remoteDataSource: FlikerPhotoRemoteDataSource
    ): FlikerRepository {
        return FlikerRepositoryImpl(localDataSource, remoteDataSource)
    }
}