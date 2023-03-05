package com.odroid.movieready.entity

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("imdb_id")
    var movieId: String = "",
    @SerializedName("poster_path")
    var posterUrl: String = "",
    @SerializedName("original_title")
    var title: String = "",
    @SerializedName("wiki_link")
    var wikiLink: String = ""
)