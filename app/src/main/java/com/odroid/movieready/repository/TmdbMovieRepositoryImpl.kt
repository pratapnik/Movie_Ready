package com.odroid.movieready.repository

import com.odroid.movieready.entity.TmdbMovie
import com.odroid.movieready.model.RetrofitBuilder

class TmdbMovieRepositoryImpl: TmdbMovieRepository {

    override suspend fun getLatestMovies(page: Int): List<TmdbMovie> {
        return RetrofitBuilder.tmdbMovieApiService.getLatestMovies(pageNumber = page).moviesList
    }
}