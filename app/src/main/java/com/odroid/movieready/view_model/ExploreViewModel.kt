package com.odroid.movieready.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.odroid.movieready.R
import com.odroid.movieready.entity.MovieDetail
import com.odroid.movieready.entity.SourceType
import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.pagination.EntertainmentPagingSource
import com.odroid.movieready.repository.TmdbMovieRepositoryImpl
import com.odroid.movieready.view.layout.destinations.NowPlayingMoviesScreenDestination
import com.odroid.movieready.view.layout.destinations.PopularMoviesScreenDestination
import com.odroid.movieready.view.layout.destinations.TopRatedMoviesScreenDestination
import com.odroid.movieready.view.layout.destinations.UpcomingMoviesScreenDestination
import com.odroid.movieready.view_intent.EntertainmentCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ExploreViewModel : ViewModel() {

    private val tmdbMovieRepository = TmdbMovieRepositoryImpl()
    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail = _movieDetail

    fun getPopularMoviesPagination(): Flow<PagingData<TmdbItem>> {
        return Pager(PagingConfig(pageSize = 20)) {
            EntertainmentPagingSource(tmdbMovieRepository, SourceType.POPULAR_MOVIES)
        }.flow.cachedIn(viewModelScope)
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

    fun fetchMovieDetails(movieId: Long) {
        viewModelScope.launch {
            tmdbMovieRepository.getMovieDetails(movieId).collect {
                _movieDetail.value = it
            }
        }
    }

    fun getMoviesCategories(): ArrayList<EntertainmentCategory> {
        val movieCategories = arrayListOf<EntertainmentCategory>()
        movieCategories.add(
            EntertainmentCategory(
                title = "popular",
                icon = R.drawable.hot,
                SourceType.POPULAR_MOVIES,
                PopularMoviesScreenDestination
            )
        )
        movieCategories.add(
            EntertainmentCategory(
                title = "upcoming",
                icon = R.drawable.ic_upcoming,
                SourceType.UPCOMING_MOVIES,
                UpcomingMoviesScreenDestination
            )
        )
        movieCategories.add(
            EntertainmentCategory(
                title = "now playing",
                icon = R.drawable.ic_play_button,
                SourceType.NOW_PLAYING,
                NowPlayingMoviesScreenDestination
            )
        )
        movieCategories.add(
            EntertainmentCategory(
                title = "top rated",
                icon = R.drawable.ic_rating,
                SourceType.TOP_RATED,
                TopRatedMoviesScreenDestination
            )
        )
        return movieCategories
    }
}