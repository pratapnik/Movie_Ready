package com.odroid.movieready.repository

import com.odroid.movieready.database.DumbCharadeSuggestion
import com.odroid.movieready.database.DumbCharadesDao
import com.odroid.movieready.network.RetrofitBuilder
import com.odroid.movieready.util.PreferenceKeys
import com.odroid.movieready.util.PreferenceUtils
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
             if(it.pageNo > 0) {
                 updateLastDumbCharadesFetchPageNumberInPref(it.pageNo)
             }
         }
    }

    override suspend fun getDumbCharadesSuggestionFromDb(): Flow<List<DumbCharadeSuggestion>?> {
        return dumbCharadesDao.getBollywoodMoviesSuggestions()
    }

    override suspend fun updateLastDumbCharadesFetchPageNumberInPref(pageNumber: Int) {
        PreferenceUtils.setIntegerPreference(PreferenceKeys.DUMB_CHARADES_LAST_FETCH_PAGE_NUMBER, pageNumber)
    }

    override suspend fun getLastDumbCharadesFetchPageNumberInPref(): Int {
        return PreferenceUtils.getIntegerPreference(PreferenceKeys.DUMB_CHARADES_LAST_FETCH_PAGE_NUMBER, -1)
    }
}