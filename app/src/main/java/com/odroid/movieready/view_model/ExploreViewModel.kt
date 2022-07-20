package com.odroid.movieready.view_model

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.odroid.movieready.entity.TmdbMovie
import com.odroid.movieready.repository.TmdbMovieRepositoryImpl
import com.odroid.movieready.pagination.PopularMoviesSource
import kotlinx.coroutines.flow.Flow

class ExploreViewModel: ViewModel() {

    private val tmdbMovieRepository = TmdbMovieRepositoryImpl()

    fun getPopularMoviesPagination(): Flow<PagingData<TmdbMovie>> {
        return Pager(PagingConfig(pageSize = 20)) {
            PopularMoviesSource(tmdbMovieRepository)
        }.flow
    }

    fun getTopRatedMoviesPagination(): Flow<PagingData<TmdbMovie>> {
        return Pager(PagingConfig(pageSize = 20)) {
            PopularMoviesSource(tmdbMovieRepository)
        }.flow
    }
}