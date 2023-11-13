package com.odroid.movieready.repository

import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.network.RetrofitBuilder

class DumbCharadesRepositoryImpl: DumbCharadesRepository {
    override suspend fun getBollywoodMovies(page: Int): List<TmdbItem> {
        return RetrofitBuilder.dumbCharadesSuggestionApi.getBollywoodMovies(
            pageNumber = page
        ).moviesList
    }
}