package com.odroid.movieready.util

import com.odroid.movieready.entity.MovieResponse
import com.odroid.movieready.model.MovieSuggestionModel

fun String?.posterUrl(): String {
    return Constants.POSTER_BASE_URL.plus(this ?: "")
}

fun MovieResponse?.toMovieSuggestionModel(): MovieSuggestionModel {
    if (this == null) {
        return MovieSuggestionModel.empty
    }
    return MovieSuggestionModel(
        movieTitle = originalTitle ?: "",
        moviePoster = posterPath ?: "",
        imdbId = imdbId ?: "",
        wikiLink = wikiLink ?: "",
        isAdult = isAdult ?: "",
        yearOfRelease = yearOfRelease ?: "",
        runtime = runtime ?: "",
        genres = genres ?: "",
        imdbRating = imdbRating ?: "",
        imdbVotes = imdbVotes ?: "",
        story = story ?: "",
        summary = story ?: "",
        tagline = tagline ?: "",
        actors = actors ?: "",
        winsNominations = winsNominations ?: "",
        releaseDate = releaseDate ?: ""
    )
}