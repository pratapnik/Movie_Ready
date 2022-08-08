package com.odroid.movieready.repository

import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.network.RetrofitBuilder

class TmdbMovieRepositoryImpl: TmdbMovieRepository {

    override suspend fun getPopularMovies(page: Int): List<TmdbItem> {
        return RetrofitBuilder.tmdbMovieApiService.getPopularMovies(pageNumber = page).moviesList
    }
}