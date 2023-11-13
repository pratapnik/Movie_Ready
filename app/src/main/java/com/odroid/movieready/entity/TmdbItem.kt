package com.odroid.movieready.entity

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class TmdbItem(
    @SerializedName("id")
    var id: Long?=null,
    @SerializedName("title")
    var title: String?=null,
    @SerializedName("overview")
    var description: String?=null,
    @SerializedName("release_date")
    var releaseDate: String?=null,
    @SerializedName("poster_path")
    var posterUrl: String?=null,
    @SerializedName("vote_average")
    var avgRating: Float?=null,
    @SerializedName("vote_count")
    var ratingCount: Long?=null,
    @SerializedName("adult")
    var isAdult: Boolean?=null,
    @SerializedName("backdrop_path")
    var backDropPath: String?=null,
    @SerializedName("genre_ids")
    var genreIds: List<Long>?=null,
    @SerializedName("popularity")
    var popularity: Double?=null,
)