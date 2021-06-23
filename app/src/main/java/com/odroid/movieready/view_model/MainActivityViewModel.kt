package com.odroid.movieready.view_model

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.odroid.movieready.base.BaseMVIViewModelWithEffect
import com.odroid.movieready.model.BollywoodMovieService
import com.odroid.movieready.model.MovieResponse
import com.odroid.movieready.util.PreferenceUtils
import com.odroid.movieready.view_intent.MainActivityViewIntent
import kotlinx.coroutines.*
import java.lang.Exception

class MainActivityViewModel : BaseMVIViewModelWithEffect<
        MainActivityViewIntent.ViewEvent,
        MainActivityViewIntent.ViewState,
        MainActivityViewIntent.ViewEffect>() {

    private var moviesList: List<MovieResponse>? = null
    var job: Job? = null

    override fun processEvent(event: MainActivityViewIntent.ViewEvent) {
        when (event) {
            is MainActivityViewIntent.ViewEvent.UpdateClicked -> {
                updateRandomMovie()
            }
            MainActivityViewIntent.ViewEvent.LoadMovies -> {
                viewState = MainActivityViewIntent.ViewState.MoviesInFlight
                viewModelScope.launch {
                    getAllMov()
                }
            }
            is MainActivityViewIntent.ViewEvent.PosterSwitchChanged -> {
                PreferenceUtils(event.context).setPosterEnabled(event.isChecked)
            }
            is MainActivityViewIntent.ViewEvent.CheckPosterSwitch -> {
                val isPosterEnabled = PreferenceUtils(event.context).isPosterEnabled()
                viewEffect = MainActivityViewIntent.ViewEffect.UpdatePosterSwitch(isPosterEnabled)
            }
        }
    }

    private suspend fun getAllMov() {
        val bollywoodMovieApi = BollywoodMovieService.getBollywoodMovieService()

        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = bollywoodMovieApi.getAllMovies()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        moviesList = response.body()
                        viewState = MainActivityViewIntent.ViewState.MoviesLoaded
                    } else {
                        viewState = MainActivityViewIntent.ViewState.MoviesLoadingFailed
                    }
                }
            }
            catch (e: Exception) {
                withContext(Dispatchers.Main){
                    viewState = MainActivityViewIntent.ViewState.MoviesLoadingFailed
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