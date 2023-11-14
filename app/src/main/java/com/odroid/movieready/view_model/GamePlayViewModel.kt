package com.odroid.movieready.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odroid.movieready.analytics.Analytics
import com.odroid.movieready.model.DumbCharadesSuggestionUiModel
import com.odroid.movieready.repository.DumbCharadesRepository
import com.odroid.movieready.util.toDumbCharadeSuggestionUiModel
import com.odroid.movieready.view.view_state.GamePlayUiState
import com.odroid.movieready.view.view_state.GamePlayViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamePlayViewModel @Inject constructor(private val dumbCharadesRepository: DumbCharadesRepository) :
    ViewModel() {

    private val _gamePlayUiState =
        MutableStateFlow(GamePlayUiState.default)
    val gamePlayUiState = _gamePlayUiState.asStateFlow()

    private var globalSuggestionsList: List<DumbCharadesSuggestionUiModel>? = null

    fun startGame() {
        _gamePlayUiState.update {
            it.copy(viewState = GamePlayViewState.GAME_STARTED)
        }
        updateNewMovie()
    }

    fun newMovieClicked() {
        _gamePlayUiState.update {
            it.copy(previousMovie = it.currentMovie)
        }
        updateNewMovie()
    }

    fun observeDumbCharadesSuggestions() {
        viewModelScope.launch(Dispatchers.IO) {
            dumbCharadesRepository.getDumbCharadesSuggestionFromDb().collect { suggestionsList ->
                if (suggestionsList.isNullOrEmpty().not()) {
                    _gamePlayUiState.update {
                        it.copy(viewState = GamePlayViewState.GAME_NOT_STARTED)
                    }
                    globalSuggestionsList = suggestionsList?.map {
                        it.toDumbCharadeSuggestionUiModel()
                    }
                }
            }
        }
    }

    private fun updateNewMovie() {
        globalSuggestionsList?.shuffled()?.last()?.run {
            _gamePlayUiState.update {
                it.copy(currentMovie = this)
            }
        }
    }

    fun getAllMov() {
        viewModelScope.launch(Dispatchers.IO) {
            dumbCharadesRepository.fetchBollywoodMovies(1)
        }
        _gamePlayUiState.update {
            it.copy(viewState = GamePlayViewState.LOADING)
        }
    }

    fun trackMovieDetailModalOpen(source: String, movieName: String) {
        Analytics.trackMovieDetailModalOpen(movieName = movieName, from = source)
    }
}