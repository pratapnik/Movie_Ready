package com.odroid.movieready.repository

import com.odroid.movieready.entity.TmdbItem

interface DumbCharadesRepository {
    suspend fun getBollywoodMovies(page: Int): List<TmdbItem>
}