package com.odroid.movieready.view_model

import androidx.lifecycle.viewModelScope
import com.odroid.movieready.base.BaseMVIViewModelWithEffect
import com.odroid.movieready.model.BollywoodMovieService
import com.odroid.movieready.entity.MovieResponse
import com.odroid.movieready.util.PreferenceUtils
import com.odroid.movieready.view_intent.MovieSuggestionViewIntent
import kotlinx.coroutines.*

class MovieSuggestionViewModel : BaseMVIViewModelWithEffect<
        MovieSuggestionViewIntent.ViewEvent,
        MovieSuggestionViewIntent.ViewState,
        MovieSuggestionViewIntent.ViewEffect>() {

    private var moviesList: List<MovieResponse>? = null
    var job: Job? = null

    override fun processEvent(event: MovieSuggestionViewIntent.ViewEvent) {
        when (event) {
            is MovieSuggestionViewIntent.ViewEvent.UpdateClicked -> {
                updateRandomMovie()
            }
            MovieSuggestionViewIntent.ViewEvent.LoadMovies -> {
                viewState = MovieSuggestionViewIntent.ViewState.MoviesInFlight
                viewModelScope.launch {
                    getAllMov()
                }
            }
            is MovieSuggestionViewIntent.ViewEvent.PosterSwitchChanged -> {
                PreferenceUtils.setPosterEnabled(event.isChecked)
                viewEffect = MovieSuggestionViewIntent.ViewEffect.UpdatePosterVisibility
            }
            is MovieSuggestionViewIntent.ViewEvent.CheckPosterSwitch -> {
                val isPosterEnabled = PreferenceUtils.isPosterEnabled()
                viewEffect =
                    MovieSuggestionViewIntent.ViewEffect.UpdatePosterSwitch(isPosterEnabled)
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
                        viewState = MovieSuggestionViewIntent.ViewState.MoviesLoaded
                    } else {
                        viewState = MovieSuggestionViewIntent.ViewState.MoviesLoadingFailed
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    viewState = MovieSuggestionViewIntent.ViewState.MoviesLoadingFailed
                }
            }
        }
    }

    private fun updateRandomMovie() {
        if (!moviesList.isNullOrEmpty()) {
            val movie = getMovie()
            viewEffect = MovieSuggestionViewIntent.ViewEffect.UpdateText(
                movie?.title ?: "",
                movie?.posterUrl ?: ""
            )
        }
    }

    private fun getMovie(): MovieResponse? {
        val randomNumber = getRandomNumber()
        val isPosterEnabled = PreferenceUtils.isPosterEnabled()
        if (isPosterEnabled) {
            var movieNumber = randomNumber
            var posterUrl = moviesList?.get(movieNumber)?.posterUrl
            var movieEntity: MovieResponse? = moviesList?.get(movieNumber)
            while (posterUrl.isNullOrEmpty()) {
                if (movieNumber >= moviesList?.size!! - 1) {
                    movieNumber = getRandomNumber()
                } else {
                    movieNumber++
                }
                movieEntity = moviesList?.get(movieNumber)
                posterUrl = movieEntity?.posterUrl ?: ""
            }
            return movieEntity
        } else {
            return moviesList?.get(randomNumber)
        }
    }

    private fun getRandomNumber(): Int {
        return if (!moviesList.isNullOrEmpty()) {
            val movieListSize = moviesList!!.size - 1
            (0..movieListSize).random()
        } else {
            0
        }
    }
}