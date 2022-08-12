package com.odroid.movieready.repository

import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.network.RetrofitBuilder

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
}