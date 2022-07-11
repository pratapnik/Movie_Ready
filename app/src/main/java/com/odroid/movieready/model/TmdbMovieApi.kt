package com.odroid.movieready.model

import com.odroid.movieready.BuildConfig
import com.odroid.movieready.entity.TmdbMovie
import com.odroid.movieready.entity.TmdbResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbMovieApi {

    @GET("movie/latest")
    suspend fun getLatestMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") pageNumber: Int
    ): TmdbResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") pageNumber: Int
    ): TmdbResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") pageNumber: Int
    ): TmdbResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") pageNumber: Int
    ): TmdbResponse
}