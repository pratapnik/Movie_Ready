package com.odroid.movieready.entity

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("imdb_id") var imdbId: String? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("wiki_link") var wikiLink: String? = null,
    @SerializedName("title_y") var titleY: String? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("is_adult") var isAdult: String? = null,
    @SerializedName("year_of_release") var yearOfRelease: String? = null,
    @SerializedName("runtime") var runtime: String? = null,
    @SerializedName("genres") var genres: String? = null,
    @SerializedName("imdb_rating") var imdbRating: String? = null,
    @SerializedName("imdb_votes") var imdbVotes: String? = null,
    @SerializedName("story") var story: String? = null,
    @SerializedName("summary") var summary: String? = null,
    @SerializedName("tagline") var tagline: String? = null,
    @SerializedName("actors") var actors: String? = null,
    @SerializedName("wins_nominations") var winsNominations: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null
)