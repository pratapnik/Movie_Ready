package com.odroid.movieready.entity

sealed class TmdbListUiState {
    object Empty : TmdbListUiState()
    object Loading : TmdbListUiState()
    class Success(val movieList: List<TmdbItem>) : TmdbListUiState()
    class Error(val message: String) : TmdbListUiState()
}