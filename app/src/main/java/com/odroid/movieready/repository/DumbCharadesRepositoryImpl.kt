package com.odroid.movieready.repository

import com.odroid.movieready.database.DumbCharadeSuggestion
import com.odroid.movieready.database.DumbCharadesDao
import com.odroid.movieready.network.RetrofitBuilder
import com.odroid.movieready.util.toDumbCharadeSuggestion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DumbCharadesRepositoryImpl @Inject constructor(val dumbCharadesDao: DumbCharadesDao): DumbCharadesRepository {
    override suspend fun fetchBollywoodMovies(page: Int){
         RetrofitBuilder.dumbCharadesSuggestionApi.getBollywoodMovies(
            pageNumber = page
        ).let {
             it.moviesList.map { tmdbItem ->
                 dumbCharadesDao.insertSuggestion(tmdbItem.toDumbCharadeSuggestion())
             }
         }
    }

    override suspend fun getDumbCharadesSuggestionFromDb(): Flow<List<DumbCharadeSuggestion>?> {
        return dumbCharadesDao.getBollywoodMoviesSuggestions()
    }
}