package com.odroid.movieready.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object BollywoodMovieService {

    private val BASE_URL = "https://gist.githubusercontent.com/pratapnik/51b3edaab55d418139c35338f1a41f36/raw/9f87248523ece2ba47c81484769ba4ef345d1e3f/"

    fun getBollywoodMovieService(): BollywoodMovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BollywoodMovieApi::class.java)
    }
}