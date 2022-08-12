package com.odroid.movieready.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.odroid.movieready.R
import com.odroid.movieready.entity.SourceType
import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.repository.TmdbMovieRepositoryImpl
import com.odroid.movieready.pagination.EntertainmentPagingSource
import com.odroid.movieready.view_intent.EntertainmentCategory
import kotlinx.coroutines.flow.Flow

class ExploreViewModel: ViewModel() {

    private val tmdbMovieRepository = TmdbMovieRepositoryImpl()

    fun getPopularMoviesPagination(): Flow<PagingData<TmdbItem>> {
        return Pager(PagingConfig(pageSize = 20)) {
            EntertainmentPagingSource(tmdbMovieRepository, SourceType.POPULAR_MOVIES)
        }.flow.cachedIn(viewModelScope)
    }

    fun getTrendingMoviesPagination(): Flow<PagingData<TmdbItem>> {
        return Pager(PagingConfig(pageSize = 20)) {
            EntertainmentPagingSource(tmdbMovieRepository, SourceType.UPCOMING_MOVIES)
        }.flow
    }

    fun getTopRatedMoviesPagination(): Flow<PagingData<TmdbItem>> {
        return Pager(PagingConfig(pageSize = 20)) {
            EntertainmentPagingSource(tmdbMovieRepository, SourceType.TOP_RATED)
        }.flow
    }

    fun getNowPlayingMoviesPagination(): Flow<PagingData<TmdbItem>> {
        return Pager(PagingConfig(pageSize = 20)) {
            EntertainmentPagingSource(tmdbMovieRepository, SourceType.NOW_PLAYING)
        }.flow
    }

    fun getUpcomingMoviesPagination(): Flow<PagingData<TmdbItem>> {
        return Pager(PagingConfig(pageSize = 20)) {
            EntertainmentPagingSource(tmdbMovieRepository, SourceType.UPCOMING_MOVIES)
        }.flow
    }

    fun getMoviesCategories(): ArrayList<EntertainmentCategory> {
        val movieCategories = arrayListOf<EntertainmentCategory>()
        movieCategories.add(
            EntertainmentCategory(
                title = "popular",
                icon = R.drawable.hot,
                SourceType.POPULAR_MOVIES
            )
        )
        movieCategories.add(
            EntertainmentCategory(
                title = "upcoming",
                icon = R.drawable.ic_rating,
                SourceType.UPCOMING_MOVIES
            )
        )
        movieCategories.add(
            EntertainmentCategory(
                title = "now playing",
                icon = R.drawable.ic_play_button,
                SourceType.NOW_PLAYING
            )
        )
        movieCategories.add(
            EntertainmentCategory(
                title = "top rated",
                icon = R.drawable.ic_rating,
                SourceType.TOP_RATED
            )
        )
        return movieCategories
    }
}