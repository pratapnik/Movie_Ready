package com.odroid.movieready.data

import com.odroid.movieready.entity.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface BollywoodMovieApi {

    @GET("51b3edaab55d418139c35338f1a41f36/raw/9f87248523ece2ba47c81484769ba4ef345d1e3f/bollywood_movie.json")
    suspend fun getAllMovies(): Response<List<MovieResponse>>
}