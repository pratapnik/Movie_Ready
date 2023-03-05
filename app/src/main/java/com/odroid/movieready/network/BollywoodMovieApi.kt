package com.odroid.movieready.network

import com.odroid.movieready.entity.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface BollywoodMovieApi {

    @GET("bollywood_movies_2.0.json")
    suspend fun getAllMovies(): Response<List<MovieResponse>>
}