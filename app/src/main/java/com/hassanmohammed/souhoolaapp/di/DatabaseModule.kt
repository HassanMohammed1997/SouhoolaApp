package com.hassanmohammed.souhoolaapp.di

import android.content.Context
import androidx.room.Room
import com.hassanmohammed.souhoolaapp.data.db.FlikerPhotoDao
import com.hassanmohammed.souhoolaapp.data.db.FlikerDatabase
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
    fun provideAppDatabase(@ApplicationContext context: Context) : FlikerDatabase =
        Room.databaseBuilder(context, FlikerDatabase::class.java, "app_database")
            .build()

    @Provides
    @Singleton
    fun providePhotoDao(database: FlikerDatabase): FlikerPhotoDao {
        return database.photoDao()
    }
}