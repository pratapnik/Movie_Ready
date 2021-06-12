package com.odroid.movieready.view_model

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.odroid.movieready.base.BaseMVIViewModelWithEffect
import com.odroid.movieready.model.BollywoodMovieService
import com.odroid.movieready.model.MovieResponse
import com.odroid.movieready.view_intent.MainActivityViewIntent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : BaseMVIViewModelWithEffect<
        MainActivityViewIntent.ViewEvent,
        MainActivityViewIntent.ViewState,
        MainActivityViewIntent.ViewEffect>() {

    private var moviesList: List<MovieResponse>? = null

    override fun processEvent(event: MainActivityViewIntent.ViewEvent) {
        when (event) {
            is MainActivityViewIntent.ViewEvent.UpdateClicked -> {
                updateRandomMovie()
            }
            MainActivityViewIntent.ViewEvent.LoadMovies -> {
                viewModelScope.launch {
                    getAllMov()
                }
            }
        }
    }

    suspend fun getAllMov() {
        val bollywoodMovieApi = BollywoodMovieService.getBollywoodMovieService()

        CoroutineScope(Dispatchers.IO).launch {
            val response = bollywoodMovieApi.getAllMovies()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    moviesList = response.body()
                    viewState = MainActivityViewIntent.ViewState.MoviesLoaded
                } else {
                }
            }
        }
    }

    private fun updateRandomMovie() {
        val randomNumber = getRandomNumber()
        if (!moviesList.isNullOrEmpty()) {
            viewEffect = MainActivityViewIntent.ViewEffect.UpdateText(
                moviesList?.get(randomNumber)?.title ?: ""
            )
        }
    }

    private fun getRandomNumber(): Int {
        return if (!moviesList.isNullOrEmpty()) {
            val movieListSize = moviesList?.size ?: 1 - 1
            (0..movieListSize).random()
        } else {
            0
        }
    }
}