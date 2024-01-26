package com.odroid.movieready.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DumbCharadesSuggestionUiModel(
    var id: Long,
    var title: String,
    var overview: String,
    var releaseDate: String,
    var posterPath: String,
    var avgRating: Float,
    var ratingCount: Long,
    val timeStamp: Long = System.currentTimeMillis(),
    val isAdult: Boolean,
    var backDropPath: String,
    var genreIds: String,
    var popularity: Double
): Parcelable {
    companion object {
        val preview = DumbCharadesSuggestionUiModel (
            id =  0L,
            title = "Jawan",
            overview = "An emotional journey of a prison warden, driven by a personal vendetta while keeping up to a promise made years ago, recruits inmates to commit outrageous crimes that shed light on corruption and injustice, in an attempt to get even with his past,  and that leads him to an unexpected reunion.",
            releaseDate = "2023-09-07",
            posterPath = "/bMISXhkBDll6JPsevdJJ1ItnW6S.jpg",
            avgRating = 7.3F,
            ratingCount = 162,
            isAdult = false,
            backDropPath = "",
            genreIds = "",
            popularity = 0.0
        )

        val empty = DumbCharadesSuggestionUiModel (
            id =  0L,
            title = "",
            overview = "",
            releaseDate = "",
            posterPath = "",
            avgRating = 0F,
            ratingCount = 0L,
            isAdult = false,
            backDropPath = "",
            genreIds = "",
            popularity = 0.0
        )
    }
}
