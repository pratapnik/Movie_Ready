package com.odroid.movieready.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieSuggestionModel(
    var movieTitle: String,
    var moviePoster: String,
    var imdbId: String,
    var wikiLink: String,
    var isAdult: String,
    var yearOfRelease: String,
    var runtime: String,
    var genres: String,
    var imdbRating: String,
    var imdbVotes: String,
    var story: String,
    var summary: String,
    var tagline: String,
    var actors: String,
    var winsNominations: String,
    var releaseDate: String
) : Parcelable {
    companion object {
        val empty = MovieSuggestionModel(
            movieTitle = "",
            moviePoster = "",
            imdbId = "",
            wikiLink = "",
            isAdult = "",
            yearOfRelease = "",
            runtime = "",
            genres = "",
            imdbRating = "",
            imdbVotes = "",
            story = "",
            summary = "",
            tagline = "",
            actors = "",
            winsNominations = "",
            releaseDate = ""
        )
    }
}
