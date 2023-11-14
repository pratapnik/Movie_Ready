package com.odroid.movieready.util

object SessionDataManager {

    private var _newMovieClickedCount = 1
    val newMovieClickedCount get() = _newMovieClickedCount

    fun incrementNewMovieClickedCount() {
        _newMovieClickedCount++
    }
}