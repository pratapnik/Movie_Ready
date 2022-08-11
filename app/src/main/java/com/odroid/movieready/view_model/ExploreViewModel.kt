package com.odroid.movieready.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.odroid.movieready.entity.SourceType
import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.repository.TmdbMovieRepositoryImpl
import com.odroid.movieready.pagination.EntertainmentPagingSource
import kotlinx.coroutines.flow.Flow

class ExploreViewModel: ViewModel() {

    private val tmdbMovieRepository = TmdbMovieRepositoryImpl()

    fun getPopularMoviesPagination(): Flow<PagingData<TmdbItem>> {
        return Pager(PagingConfig(pageSize = 20)) {
            EntertainmentPagingSource(tmdbMovieRepository, SourceType.POPULAR_MOVIES)
        }.flow.cachedIn(viewModelScope)
    }

    fun getLatestMoviesPagination(): Flow<PagingData<TmdbItem>> {
        return Pager(PagingConfig(pageSize = 20)) {
            EntertainmentPagingSource(tmdbMovieRepository, SourceType.LATEST_MOVIES)
        }.flow
    }

    fun getTrendingMoviesPagination(): Flow<PagingData<TmdbItem>> {
        return Pager(PagingConfig(pageSize = 20)) {
            EntertainmentPagingSource(tmdbMovieRepository, SourceType.TRENDING_MOVIES)
        }.flow
    }
}