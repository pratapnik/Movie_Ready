package com.odroid.movieready.entity

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id")
    val movieId: Long,
    @SerializedName("title")
    val movieTitle: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("overview")
    var description: String,
    @SerializedName("vote_average")
    var avgRating: Float,
    @SerializedName("vote_count")
    var ratingCount: Long
)