package com.odroid.movieready.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("imdb_id")
    val movieId: String = "",
    @SerializedName("poster_path")
    val posterUrl: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("wiki_link")
    val wikiLink: String = ""
)