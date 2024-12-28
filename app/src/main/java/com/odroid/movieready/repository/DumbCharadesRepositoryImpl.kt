package com.odroid.movieready.repository

import android.util.Log
import com.odroid.movieready.database.DumbCharadeSuggestion
import com.odroid.movieready.database.DumbCharadesDao
import com.odroid.movieready.entity.TmdbDetailDto
import com.odroid.movieready.model.MovieDetailModel
import com.odroid.movieready.model.ProductionCompanyUiModel
import com.odroid.movieready.network.RetrofitBuilder
import com.odroid.movieready.util.CommonUtil.toIndianRupeeWithoutDecimals
import com.odroid.movieready.util.CommonUtil.toUSDWithSuffix
import com.odroid.movieready.util.DateUtil
import com.odroid.movieready.util.PreferenceKeys
import com.odroid.movieready.util.PreferenceUtils
import com.odroid.movieready.util.toDumbCharadeSuggestion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DumbCharadesRepositoryImpl @Inject constructor(val dumbCharadesDao: DumbCharadesDao) :
    DumbCharadesRepository {
    override suspend fun fetchBollywoodMovies(page: Int) {
        RetrofitBuilder.dumbCharadesSuggestionApi.getBollywoodMovies(
            pageNumber = page
        ).let {
            Log.d("ishaara_logs", "fetchBollywoodMoviesCalled with page no. ${it.pageNo}")
            it.moviesList.map { tmdbItem ->
                dumbCharadesDao.insertSuggestion(tmdbItem.toDumbCharadeSuggestion())
            }
            if (it.pageNo > 0) {
                updateLastDumbCharadesFetchPageNumberInPref(it.pageNo)
            }
        }
    }

    override suspend fun getDumbCharadesSuggestionFromDb(): Flow<List<DumbCharadeSuggestion>?> {
        return dumbCharadesDao.getBollywoodMoviesSuggestions()
    }

    override suspend fun updateLastDumbCharadesFetchPageNumberInPref(pageNumber: Int) {
        PreferenceUtils.setIntegerPreference(
            PreferenceKeys.DUMB_CHARADES_LAST_FETCH_PAGE_NUMBER,
            pageNumber
        )
    }

    override suspend fun getLastDumbCharadesFetchPageNumberInPref(): Int {
        return PreferenceUtils.getIntegerPreference(
            PreferenceKeys.DUMB_CHARADES_LAST_FETCH_PAGE_NUMBER,
            -1
        )
    }

    override suspend fun getNumberOfSuggestionsInDb(): Int {
        return dumbCharadesDao.getNumberOfSuggestionsInDb()
    }

    override suspend fun saveFirstDumbCharadesApiCallTime(time: Long) {
        PreferenceUtils.setLongPreference(PreferenceKeys.FIRST_DUMB_CHARADES_API_CALL_TIME, time)
    }

    override suspend fun getFirstDumbCharadesApiCallTime(): Long {
        return PreferenceUtils.getLongPreference(
            PreferenceKeys.FIRST_DUMB_CHARADES_API_CALL_TIME,
            -1
        )
    }

    override suspend fun clearDb() {
        dumbCharadesDao.clearDumbCharadesSuggestions()
    }

    override suspend fun getMovieDetails(movieId: Long): MovieDetailModel {
        return RetrofitBuilder.dumbCharadesSuggestionApi.getMovieDetails(movieId = movieId).toUiModel()
    }

    fun TmdbDetailDto.toUiModel(): MovieDetailModel {
        return MovieDetailModel(
            id = id ?: 0,
            title = title.orEmpty(),
            tagline = tagline ?: "",
            overview = overview?: "",
            posterPath = posterPath?:"",
            backdropPath = backdropPath?:"",
            genres = genres?.map { it.name }?.joinToString(", ") ?: "",
            runtime = runtime?.let { "${it / 60}h ${it % 60}m" } ?: "Unknown runtime",
            releaseDate = releaseDate?.let { DateUtil.convertDateFormat(it) } ?: "",
            voteAverage = voteAverage?.let { "$it/10" } ?: "N/A",
            productionCompanies = productionCompanies?.map {
                ProductionCompanyUiModel(
                    id = it.id ?: 0,
                    name = it.name.orEmpty(),
                    logoPath = it.logoPath ?: ""
                )
            } ?: emptyList(),
            budget = budget?.toUSDWithSuffix() ?: "",
            revenue = revenue?.toUSDWithSuffix() ?: ""
        )
    }
}