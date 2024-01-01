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
