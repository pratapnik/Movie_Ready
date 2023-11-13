package com.odroid.movieready.di

import android.content.Context
import com.odroid.movieready.database.AppDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(
            context
        )
    }

    @Provides
    @Singleton
    fun providesDumbCharadesDao(appDatabase: AppDatabase) = appDatabase.dumbCharadesDao()
}