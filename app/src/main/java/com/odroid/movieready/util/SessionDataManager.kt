package com.odroid.movieready.util

import com.odroid.movieready.model.DumbCharadesSuggestionUiModel

object SessionDataManager {

    private var _newMovieClickedCount = 1
    val newMovieClickedCount get() = _newMovieClickedCount

    val alreadySuggestedMovies: MutableSet<Long> = mutableSetOf()

    fun incrementNewMovieClickedCount() {
        _newMovieClickedCount++
    }
}