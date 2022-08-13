package com.odroid.movieready.repository

import com.odroid.movieready.entity.MovieDetail
import com.odroid.movieready.entity.TmdbItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface TmdbMovieRepository {

    suspend fun getPopularMovies(page: Int): List<TmdbItem>
    suspend fun getUpcomingMovies(page: Int): List<TmdbItem>
    suspend fun getTopRatedMovies(page: Int): List<TmdbItem>
    suspend fun getNowPlayingMovies(page: Int): List<TmdbItem>
    suspend fun getMovieDetails(movieId: Long): Flow<MovieDetail?>
}