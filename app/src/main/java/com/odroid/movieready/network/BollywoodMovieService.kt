package com.odroid.movieready.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object BollywoodMovieService {

    private val BASE_URL = "https://gist.githubusercontent.com/pratapnik/cf3e54d6fd3d705438458ab6f97aeac9/raw/d08764cdc0b0fec4aa137eda83accb5fd5357a87/"

    fun getBollywoodMovieService(): BollywoodMovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BollywoodMovieApi::class.java)
    }
}