package com.odroid.movieready.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odroid.movieready.analytics.Analytics
import com.odroid.movieready.entity.Constants
import com.odroid.movieready.model.DumbCharadesSuggestionUiModel
import com.odroid.movieready.repository.DumbCharadesRepository
import com.odroid.movieready.util.CommonUtil
import com.odroid.movieready.util.SessionDataManager
import com.odroid.movieready.util.coroutineExceptionHandler
import com.odroid.movieready.util.toDumbCharadeSuggestionUiModel
import com.odroid.movieready.view.view_state.GamePlayUiState
import com.odroid.movieready.view.view_state.GamePlayViewState
import com.odroid.movieready.view.view_state.OnScreenMessageState
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

    private val _onScreenMessageState =
        MutableStateFlow(OnScreenMessageState.default)

    val onScreenMessageState = _onScreenMessageState.asStateFlow()

    fun startGame() {
        if (globalSuggestionsList.isNullOrEmpty()) {
            _onScreenMessageState.update {
                it.copy(
                    message = "Please check your internet and RESTART the app!",
                    isTriggered = true
                )
            }
        } else {
            _gamePlayUiState.update {
                it.copy(viewState = GamePlayViewState.GAME_STARTED)
            }
            updateNewMovie()
        }
    }

    fun newMovieClicked() {
        _gamePlayUiState.update {
            it.copy(previousMovie = it.currentMovie)
        }
        updateNewMovie()
        Log.d("ishaara_logs", "newMovieClickedCount --> ${SessionDataManager.newMovieClickedCount}")
        Log.d("ishaara_logs", "Final list size --> ${globalSuggestionsList?.size}")

        if (SessionDataManager.newMovieClickedCount % 5 == 0) {
            fetchMoviesFromRemote()
        }
        SessionDataManager.incrementNewMovieClickedCount()
    }

    fun observeDumbCharadesSuggestions() {
        viewModelScope.launch(Dispatchers.IO) {
            dumbCharadesRepository.getDumbCharadesSuggestionFromDb().collect { suggestionsList ->
                if (suggestionsList.isNullOrEmpty().not()) {
                    globalSuggestionsList = suggestionsList?.map {
                        it.toDumbCharadeSuggestionUiModel()
                    }
                }
            }
        }
    }

    fun updateOnScreenMessageState(onScreenMessageState: OnScreenMessageState) {
        viewModelScope.launch {
            _onScreenMessageState.emit(onScreenMessageState)
        }
    }

    private fun updateNewMovie() {
        globalSuggestionsList?.run {
            CommonUtil.getRandomUniqueItem(
                collection = this,
                alreadySuggestedMovies = SessionDataManager.alreadySuggestedMovies
            )?.run {
                SessionDataManager.alreadySuggestedMovies.add(this.id)
                _gamePlayUiState.update {
                    it.copy(currentMovie = this)
                }
            }
        }
    }

    fun fetchMoviesFromRemote() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val offlineAvailableSuggestionsCount =
                dumbCharadesRepository.getNumberOfSuggestionsInDb()

            Log.d(
                "ishaara_logs",
                "offlineAvailableSuggestionsCount --> $offlineAvailableSuggestionsCount"
            )

            if (offlineAvailableSuggestionsCount <= Constants.SUGGESTIONS_STORE_MAX_LIMIT) {
                val lastPageNumber =
                    dumbCharadesRepository.getLastDumbCharadesFetchPageNumberInPref()
                val pageNumber = if (lastPageNumber > 0) {
                    lastPageNumber + 1
                } else {
                    1
                }
                if (pageNumber == 1) {
                    dumbCharadesRepository.saveFirstDumbCharadesApiCallTime(time = System.currentTimeMillis())
                }
                dumbCharadesRepository.fetchBollywoodMovies(page = pageNumber)
            }
        }
    }

    fun refreshDB() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val firstDumbCharadesApiCallTime =
                dumbCharadesRepository.getFirstDumbCharadesApiCallTime()
            Log.d(
                "ishaara_logs",
                "firstDumbCharadesApiCallTime --> ${firstDumbCharadesApiCallTime}"
            )

            val currentTime = System.currentTimeMillis()
            if (firstDumbCharadesApiCallTime >= 0 && currentTime - firstDumbCharadesApiCallTime >= Constants.REFRESH_SUGGESTIONS_TIME_PERIOD) {
                Log.d(
                    "ishaara_logs",
                    "refreshing DB --> ${currentTime - firstDumbCharadesApiCallTime}"
                )
                dumbCharadesRepository.updateLastDumbCharadesFetchPageNumberInPref(pageNumber = -1)
                dumbCharadesRepository.clearDb()
            }
            fetchMoviesFromRemote()
        }
    }

    fun updateUiState(gamePlayViewState: GamePlayViewState) {
        _gamePlayUiState.update {
            it.copy(
                viewState = gamePlayViewState
            )
        }
    }

    fun trackMovieDetailModalOpen(source: String, movieName: String) {
        Analytics.trackMovieDetailModalOpen(movieName = movieName, from = source)
    }
}