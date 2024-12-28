package com.odroid.movieready.network

import com.odroid.movieready.BuildConfig
import com.odroid.movieready.entity.TmdbDetailDto
import com.odroid.movieready.entity.TmdbResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DumbCharadesSuggestionApi {

    @GET("discover/movie")
    suspend fun getBollywoodMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") pageNumber: Int,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_original_language") originalLanguage: String = "hi",
    ): TmdbResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): TmdbDetailDto
}