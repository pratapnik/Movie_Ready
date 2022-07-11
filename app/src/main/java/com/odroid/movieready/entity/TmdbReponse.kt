package com.odroid.movieready.entity

import com.google.gson.annotations.SerializedName

data class TmdbResponse(
    @SerializedName("page")
    var pageNo: Int = 0,
    @SerializedName("results")
    var moviesList: List<TmdbMovie> = emptyList(),
    @SerializedName("total_pages")
    var totalNumberOfPages: Int = 0
)