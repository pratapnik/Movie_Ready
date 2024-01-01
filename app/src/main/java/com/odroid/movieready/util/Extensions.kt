package com.odroid.movieready.util

import com.odroid.movieready.database.DumbCharadeSuggestion
import com.odroid.movieready.entity.Constants
import com.odroid.movieready.entity.MovieResponse
import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.model.DumbCharadesSuggestionUiModel
import com.odroid.movieready.model.MovieSuggestionModel
import kotlinx.coroutines.CoroutineExceptionHandler

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

fun String.getListFromStringUsingSeparator(separator: String): List<String> {
    return split(separator).filter {
        it.isNotEmpty()
    }
}

fun TmdbItem.toDumbCharadeSuggestion(): DumbCharadeSuggestion {
    return DumbCharadeSuggestion(
        id = id ?: -1,
        title = title ?: "",
        overview = description ?: "",
        releaseDate = releaseDate ?: "",
        posterPath = posterUrl ?: "",
        avgRating = avgRating ?: 0f,
        ratingCount = ratingCount ?: 0L,
        isAdult = isAdult ?: false,
        backDropPath = backDropPath ?: "",
        genreIds = genreIds?.joinToString(",") ?: "",
        popularity = popularity ?: 0.0
    )
}

fun DumbCharadeSuggestion.toDumbCharadeSuggestionUiModel(): DumbCharadesSuggestionUiModel {
    return DumbCharadesSuggestionUiModel(
        id = id,
        title = title,
        overview = overview,
        releaseDate = DateUtil.convertDateFormat(inputDate = releaseDate),
        posterPath = posterPath.posterUrl(),
        avgRating = avgRating,
        ratingCount = ratingCount,
        isAdult = isAdult,
        backDropPath = backDropPath,
        genreIds = genreIds,
        popularity = popularity
    )
}

val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
    throwable.printStackTrace()
}