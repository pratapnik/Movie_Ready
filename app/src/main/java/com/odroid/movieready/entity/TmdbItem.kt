package com.odroid.movieready.entity

import com.google.gson.annotations.SerializedName

data class TmdbItem(
    @SerializedName("id")
    var id: Long,
    @SerializedName("title")
    var title: String,
    @SerializedName("overview")
    var description: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("poster_path")
    var posterUrl: String,
    @SerializedName("vote_average")
    var avgRating: Float,
    @SerializedName("vote_count")
    var ratingCount: Long
)
