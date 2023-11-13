package com.odroid.movieready.repository

import com.odroid.movieready.entity.TmdbItem

interface DumbCharadesRepository {
    suspend fun fetchBollywoodMovies(page: Int)
}