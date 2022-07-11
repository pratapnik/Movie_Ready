package com.odroid.movieready.repository

import com.odroid.movieready.entity.TmdbMovie

interface TmdbMovieRepository {

    suspend fun getLatestMovies(page:Int): List<TmdbMovie>
}