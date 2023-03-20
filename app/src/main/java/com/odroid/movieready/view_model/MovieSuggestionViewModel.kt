package com.odroid.movieready.view_model

import androidx.lifecycle.viewModelScope
import com.odroid.movieready.base.BaseMVIViewModelWithEffect
import com.odroid.movieready.network.BollywoodMovieService
import com.odroid.movieready.entity.MovieResponse
import com.odroid.movieready.util.Analytics
import com.odroid.movieready.view_intent.MovieSuggestionViewIntent
import kotlinx.coroutines.*

class MovieSuggestionViewModel : BaseMVIViewModelWithEffect<
        MovieSuggestionViewIntent.ViewEvent,
        MovieSuggestionViewIntent.ViewState,
        MovieSuggestionViewIntent.ViewEffect>() {

    private var moviesList: List<MovieResponse>? = null
    private var moviesWithPoster: List<MovieResponse>? = null
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
        }
    }

    private suspend fun getAllMov() {
        val bollywoodMovieApi = BollywoodMovieService.getBollywoodMovieService()

        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = bollywoodMovieApi.getAllMovies()
                delay(500)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        moviesList = response.body()
                        moviesWithPoster = moviesList?.filter {
                            it.posterUrl.isNotEmpty()
                        }
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
            val movieTitle = movie?.title ?: ""
            Analytics.trackMovieUpdatedEvent(movieTitle)
            viewEffect = MovieSuggestionViewIntent.ViewEffect.UpdateText(
                movieTitle, movie?.posterUrl ?: ""
            )
        }
    }

    private fun getMovie(): MovieResponse? {
        moviesWithPoster?.let {
            return it.shuffled().last()
        }
        moviesList?.let {
            return it.shuffled().last()
        }
        return null
    }

}