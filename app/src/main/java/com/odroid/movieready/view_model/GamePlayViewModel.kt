package com.odroid.movieready.view_model

import androidx.lifecycle.ViewModel
import com.odroid.movieready.entity.MovieResponse
import com.odroid.movieready.network.BollywoodMovieService
import com.odroid.movieready.util.Analytics
import com.odroid.movieready.util.toMovieSuggestionModel
import com.odroid.movieready.view.view_state.GamePlayUiState
import com.odroid.movieready.view.view_state.GamePlayViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GamePlayViewModel @Inject constructor(): ViewModel() {

    private val _gamePlayUiState =
        MutableStateFlow(GamePlayUiState.default)
    val gamePlayUiState = _gamePlayUiState.asStateFlow()

    private var moviesList: List<MovieResponse>? = null
    private var moviesWithPoster: List<MovieResponse>? = null
    var job: Job? = null

    fun startGame() {
        _gamePlayUiState.update {
            it.copy(viewState = GamePlayViewState.GAME_STARTED)
        }
        updateRandomMovie()
    }

    fun newMovieClicked() {
        _gamePlayUiState.update {
            it.copy(previousMovie = it.currentMovie)
        }
        updateRandomMovie()
    }

    fun getAllMov() {
        _gamePlayUiState.update {
            it.copy(viewState = GamePlayViewState.LOADING)
        }
        val bollywoodMovieApi = BollywoodMovieService.getBollywoodMovieService()

        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = bollywoodMovieApi.getAllMovies()
                delay(500)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        moviesList = response.body()
                        moviesWithPoster = moviesList?.filter {
                            it.posterPath.isNullOrEmpty().not()
                        }
                        _gamePlayUiState.update {
                            it.copy(viewState = GamePlayViewState.GAME_NOT_STARTED)
                        }
                    } else {
                        _gamePlayUiState.update {
                            it.copy(viewState = GamePlayViewState.LOAD_ERROR)
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _gamePlayUiState.update {
                        it.copy(viewState = GamePlayViewState.LOAD_ERROR)
                    }
                }
            }
        }
    }

    private fun updateRandomMovie() {
        if (!moviesList.isNullOrEmpty()) {
            val movie = getMovie()
            val movieTitle = movie?.originalTitle ?: ""
            Analytics.trackMovieUpdatedEvent(movieTitle)
            _gamePlayUiState.update {
                it.copy(currentMovie = movie.toMovieSuggestionModel())
            }
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