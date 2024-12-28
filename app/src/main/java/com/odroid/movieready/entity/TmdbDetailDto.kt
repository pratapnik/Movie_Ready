package com.odroid.movieready.entity

import com.google.gson.annotations.SerializedName

data class TmdbDetailDto(
    @SerializedName("adult")
    val adult: Boolean? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollectionDto? = null,

    @SerializedName("budget")
    val budget: Long? = null,

    @SerializedName("genres")
    val genres: List<GenreDto>? = null,

    @SerializedName("homepage")
    val homepage: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("imdb_id")
    val imdbId: String? = null,

    @SerializedName("origin_country")
    val originCountry: List<String>? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("popularity")
    val popularity: Double? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyDto>? = null,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryDto>? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    @SerializedName("revenue")
    val revenue: Long? = null,

    @SerializedName("runtime")
    val runtime: Int? = null,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto>? = null,

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("tagline")
    val tagline: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("video")
    val video: Boolean? = null,

    @SerializedName("vote_average")
    val voteAverage: Double? = null,

    @SerializedName("vote_count")
    val voteCount: Int? = null
)

data class BelongsToCollectionDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null
)

data class GenreDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null
)

data class ProductionCompanyDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("logo_path")
    val logoPath: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("origin_country")
    val originCountry: String? = null
)

data class ProductionCountryDto(
    @SerializedName("name")
    val name: String? = null
)

data class SpokenLanguageDto(
    @SerializedName("english_name")
    val englishName: String? = null,
    @SerializedName("name")
    val name: String? = null
)
