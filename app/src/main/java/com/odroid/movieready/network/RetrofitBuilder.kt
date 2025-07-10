package com.odroid.movieready.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.odroid.movieready.BuildConfig
import com.odroid.movieready.base.IshaaraApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient().build())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        val chuckerInterceptor = IshaaraApplication.context?.applicationContext?.run {
            ChuckerInterceptor.Builder(this)
                .maxContentLength(250_000L)
                .alwaysReadResponseBody(true)
                .createShortcut(false)
                .build()
        }
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(logging)
            chuckerInterceptor?.run {
                httpClient.addInterceptor(this)
            }
        }
        return httpClient
    }

    val tmdbMovieApiService: TmdbMovieApi = retrofit.create(TmdbMovieApi::class.java)
    val dumbCharadesSuggestionApi: DumbCharadesSuggestionApi =
        retrofit.create(DumbCharadesSuggestionApi::class.java)
}