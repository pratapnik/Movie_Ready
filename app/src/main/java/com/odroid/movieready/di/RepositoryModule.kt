package com.odroid.movieready.di

import com.odroid.movieready.database.DumbCharadesDao
import com.odroid.movieready.repository.DumbCharadesRepository
import com.odroid.movieready.repository.DumbCharadesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun providesDumbCharadesRepository(dumbCharadesDao: DumbCharadesDao): DumbCharadesRepository {
        return DumbCharadesRepositoryImpl(dumbCharadesDao)
    }
}