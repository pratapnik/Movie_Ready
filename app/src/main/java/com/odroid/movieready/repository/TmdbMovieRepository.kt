package com.odroid.movieready.repository

import com.odroid.movieready.entity.TmdbMovie

interface TmdbMovieRepository {

    suspend fun getPopularMovies(page:Int): List<TmdbMovie>
}