package com.odroid.movieready.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odroid.movieready.model.MovieDetailModel
import com.odroid.movieready.repository.DumbCharadesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val dumbCharadesRepository: DumbCharadesRepository) :
    ViewModel() {

    private val _movieDetails = MutableStateFlow(MovieDetailModel())

    val movieDetails = _movieDetails.asStateFlow()

    fun getMovieDetails(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _movieDetails.update {
                dumbCharadesRepository.getMovieDetails(movieId = movieId)
            }
        }

    }
}