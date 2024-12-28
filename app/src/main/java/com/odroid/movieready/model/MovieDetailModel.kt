package com.odroid.movieready.model

data class MovieDetailModel(
    val id: Int = -1,
    val title: String = "",
    val tagline: String = "",
    val overview: String= "",
    val posterPath: String= "",
    val backdropPath: String= "",
    val genres: String = "",
    val runtime: String= "", // e.g., "3h 21m"
    val releaseDate: String= "",
    val voteAverage: String= "", // e.g., "6.4/10"
    val productionCompanies: List<ProductionCompanyUiModel> = emptyList(),
    val budget: String = "",
    val revenue: String = ""
) {
    companion object {
        val preview = MovieDetailModel(
            id = 781732,
            title = "Animal",
            tagline = "A father-son bond carved in blood",
            overview = "The hardened son of a powerful industrialist returns home after years abroad and vows to take bloody revenge on those threatening his father's life.",
            posterPath = "https://image.tmdb.org/t/p/w500/hr9rjR3J0xBBKmlJ4n3gHId9ccx.jpg",
            backdropPath = "https://image.tmdb.org/t/p/w500/wRG2lEV8KfT9Zj1hqzpfXWPI8Oe.jpg",
            genres = "Action, Crime, Drama",
            runtime = "3h 21m",
            releaseDate = "2023-12-01",
            voteAverage = "6.4/10",
            productionCompanies = listOf(
                ProductionCompanyUiModel(
                    id = 3522,
                    name = "T-Series",
                    logoPath = "https://image.tmdb.org/t/p/w500/d3u51JgEP5KwPfxS13ocqvtzZeX.png"
                ),
                ProductionCompanyUiModel(
                    id = 94245,
                    name = "Bhadrakali Pictures",
                    logoPath = null
                ),
                ProductionCompanyUiModel(
                    id = 215410,
                    name = "ST Film",
                    logoPath = null
                )
            ),
            budget = "13000000",
            revenue = "108300000"
        )
    }
}

data class ProductionCompanyUiModel(
    val id: Int,
    val name: String,
    val logoPath: String?
)
