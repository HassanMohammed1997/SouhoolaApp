package com.hassanmohammed.souhoolaapp.di

import android.content.Context
import androidx.room.Room
import com.hassanmohammed.souhoolaapp.data.db.FlickrPhotoDao
import com.hassanmohammed.souhoolaapp.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .build()

    @Provides
    @Singleton
    fun providePhotoDao(database: AppDatabase): FlickrPhotoDao {
        return database.photoDao()
    }
}