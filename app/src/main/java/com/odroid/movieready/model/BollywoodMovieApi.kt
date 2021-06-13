package com.odroid.movieready.model

import retrofit2.Response
import retrofit2.http.GET

interface BollywoodMovieApi {

    @GET("bollywood_movie.json")
    suspend fun getAllMovies(): Response<List<MovieResponse>>
}