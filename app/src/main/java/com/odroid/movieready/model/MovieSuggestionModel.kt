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

        val preview = MovieSuggestionModel(
            movieTitle = "Uri: The Surgical Strike",
            moviePoster = "https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/URI_-_New_poster.jpg/220px-URI_-_New_poster.jpg",
            imdbRating = "8.4",
            summary = "Indian army special forces execute a covert operation  avenging the killing of fellow army men at their base by a terrorist group.",
            story = "Divided over five chapters  the film chronicles the events of the surgical strike conducted by the Indian military against suspected militants in Pakistan occupied Kashmir. It tells the story of the 11 tumultuous events over which the operation was carried out. Indian army special forces carry out a covert operation to avenge the killing of fellow army men at their base by a terrorist group.",
            isAdult = "0",
            runtime = "138",
            genres = "Action|Drama|War",
            imdbVotes = "35112",
            actors = "Vicky Kaushal|Paresh Rawal|Mohit Raina|Yami Gautam|Kirti Kulhari|Rajit Kapoor|Ivan Rodrigues|Manasi Parekh|Swaroop Sampat|Riva Arora|Yogesh Soman|Fareed Ahmed|Akashdeep Arora|Kallol Banerjee|",
            winsNominations = "4 wins",
            releaseDate = "11 January 2019 (USA)",
            imdbId = "tt8291224",
            wikiLink = "https://en.wikipedia.org/wiki/Uri:_The_Surgical_Strike",
            yearOfRelease = "2019",
            tagline = ""
        )
    }
}
