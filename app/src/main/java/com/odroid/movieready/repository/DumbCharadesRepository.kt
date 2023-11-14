package com.odroid.movieready.repository

import com.odroid.movieready.database.DumbCharadeSuggestion
import kotlinx.coroutines.flow.Flow

interface DumbCharadesRepository {
    suspend fun fetchBollywoodMovies(page: Int)
    suspend fun getDumbCharadesSuggestionFromDb(): Flow<List<DumbCharadeSuggestion>?>
    suspend fun updateLastDumbCharadesFetchPageNumberInPref(pageNumber: Int)
    suspend fun getLastDumbCharadesFetchPageNumberInPref(): Int
}