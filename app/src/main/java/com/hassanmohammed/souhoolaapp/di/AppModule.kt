package com.hassanmohammed.souhoolaapp.di

import com.hassanmohammed.souhoolaapp.data.datasource.local.FlickrPhotoLocalDataSource
import com.hassanmohammed.souhoolaapp.data.datasource.local.FlickrPhotoLocaleDataSourceImpl
import com.hassanmohammed.souhoolaapp.data.datasource.remote.FlickrPhotoRemoteDataSource
import com.hassanmohammed.souhoolaapp.data.datasource.remote.FlickrPhotoRemoteDataSourceImpl
import com.hassanmohammed.souhoolaapp.data.db.AppDatabase
import com.hassanmohammed.souhoolaapp.data.db.FlickrPhotoDao
import com.hassanmohammed.souhoolaapp.data.remote.FlickrService
import com.hassanmohammed.souhoolaapp.data.repository.FlickrRepository
import com.hassanmohammed.souhoolaapp.data.repository.FlickrRepositoryImpl
import com.hassanmohammed.souhoolaapp.domain.usecases.GetFlikerPhotoFromApiUseCase
import com.hassanmohammed.souhoolaapp.domain.usecases.GetFlikerPhotoFromApiUseCaseImpl
import com.hassanmohammed.souhoolaapp.domain.usecases.GetFlikerPhotoFromDatabaseUseCase
import com.hassanmohammed.souhoolaapp.domain.usecases.GetFlikerPhotoFromDatabaseUseCaseImpl
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
    fun provideFlikerPhotoLocaleDataSource(flickrPhotoDao: FlickrPhotoDao): FlickrPhotoLocalDataSource {
        return FlickrPhotoLocaleDataSourceImpl(flickrPhotoDao)
    }

    @Provides
    @Singleton
    fun provideFlikerPhotoRemoteLocalDataSource(
        api: FlickrService,
        db: AppDatabase
    ): FlickrPhotoRemoteDataSource {
        return FlickrPhotoRemoteDataSourceImpl(api, db)
    }

    @Provides
    @Singleton
    fun provideFlikerRepository(
        localDataSource: FlickrPhotoLocalDataSource,
        remoteDataSource: FlickrPhotoRemoteDataSource
    ): FlickrRepository {
        return FlickrRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetFlickrPhotoFromApiUseCase(repository: FlickrRepository): GetFlikerPhotoFromApiUseCase {
        return GetFlikerPhotoFromApiUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideFlickrPhotoFromDatabaseUseCase(repository: FlickrRepository): GetFlikerPhotoFromDatabaseUseCase {
        return GetFlikerPhotoFromDatabaseUseCaseImpl(repository)
    }
}