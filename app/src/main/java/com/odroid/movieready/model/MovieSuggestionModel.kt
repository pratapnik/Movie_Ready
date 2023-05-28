package com.odroid.movieready.model

data class MovieSuggestionModel(
    val movieTitle: String,
    val moviePoster: String
) {
    companion object {
        val preview = MovieSuggestionModel(
            movieTitle = "",
            moviePoster = ""
        )
    }
}
