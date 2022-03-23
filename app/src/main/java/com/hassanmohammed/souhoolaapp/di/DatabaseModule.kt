package com.hassanmohammed.souhoolaapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hassanmohammed.souhoolaapp.data.db.PhotoDao
import com.hassanmohammed.souhoolaapp.data.db.PhotoDatabase
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
    fun provideAppDatabase(@ApplicationContext context: Context) : PhotoDatabase =
        Room.databaseBuilder(context, PhotoDatabase::class.java, "app_database")
            .build()

    @Provides
    @Singleton
    fun providePhotoDao(database: PhotoDatabase): PhotoDao {
        return database.photoDao()
    }
}