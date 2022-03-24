package com.odroid.movieready.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object BollywoodMovieService {

    fun getBollywoodMovieService(): BollywoodMovieApi {
        return NetworkClient
            .getNetworkClient()
            .create(BollywoodMovieApi::class.java)
    }
}