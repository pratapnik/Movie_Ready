package com.odroid.movieready.repository

import com.odroid.movieready.entity.TmdbItem

interface TmdbMovieRepository {

    suspend fun getPopularMovies(page:Int): List<TmdbItem>
}