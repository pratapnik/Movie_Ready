package com.odroid.movieready.repository

import com.odroid.movieready.entity.MovieDetail
import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class TmdbMovieRepositoryImpl : TmdbMovieRepository {

    override suspend fun getPopularMovies(page: Int): List<TmdbItem> {
        return RetrofitBuilder.tmdbMovieApiService.getPopularMovies(pageNumber = page).moviesList
    }

    override suspend fun getUpcomingMovies(page: Int): List<TmdbItem> {
        return RetrofitBuilder.tmdbMovieApiService.getUpcomingMovies(pageNumber = page).moviesList
    }

    override suspend fun getTopRatedMovies(page: Int): List<TmdbItem> {
        return RetrofitBuilder.tmdbMovieApiService.getTopRatedMovies(pageNumber = page).moviesList
    }

    override suspend fun getNowPlayingMovies(page: Int): List<TmdbItem> {
        return RetrofitBuilder.tmdbMovieApiService.getNowPlayingMovies(pageNumber = page).moviesList
    }

    override suspend fun getMovieDetails(movieId: Long): Flow<MovieDetail?> {
            return flow {
                emit(RetrofitBuilder.tmdbMovieApiService.getMovieDetails(movieId= movieId).body())
            }.flowOn(Dispatchers.IO)
    }
}