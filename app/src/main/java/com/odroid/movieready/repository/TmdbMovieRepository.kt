package com.odroid.movieready.repository

import com.odroid.movieready.entity.TmdbItem

interface TmdbMovieRepository {

    suspend fun getPopularMovies(page:Int): List<TmdbItem>
    suspend fun getUpcomingMovies(page:Int): List<TmdbItem>
    suspend fun getTopRatedMovies(page:Int): List<TmdbItem>
    suspend fun getNowPlayingMovies(page:Int): List<TmdbItem>
}