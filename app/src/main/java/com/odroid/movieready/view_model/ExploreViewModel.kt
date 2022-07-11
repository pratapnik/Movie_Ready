package com.odroid.movieready.view_model

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.odroid.movieready.entity.TmdbMovie
import com.odroid.movieready.repository.TmdbMovieRepositoryImpl
import com.odroid.movieready.util.TmdbLatestMoviesSource
import kotlinx.coroutines.flow.Flow

class ExploreViewModel: ViewModel() {

    private val tmdbMovieRepository = TmdbMovieRepositoryImpl()

    fun getLatestMoviesPagination(): Flow<PagingData<TmdbMovie>> {
        return Pager(PagingConfig(pageSize = 20)) {
            TmdbLatestMoviesSource(tmdbMovieRepository)
        }.flow
    }
}