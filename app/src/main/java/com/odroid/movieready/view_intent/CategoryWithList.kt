package com.odroid.movieready.view_intent

import com.odroid.movieready.R
import com.odroid.movieready.entity.MovieResponse

sealed class CategoryWithList(
    var title: String,
    var icon: Int,
    var list: ArrayList<MovieResponse>
) {

    object Trending : CategoryWithList("Trending", R.drawable.hot, getMoviesList())
    object NowPlaying : CategoryWithList("Now Playing", R.drawable.ic_play_button, getMoviesList())
    object TopRated : CategoryWithList("Top Rated", R.drawable.ic_rating, getMoviesList())
}

fun getMoviesList(): ArrayList<MovieResponse> {
    val items = arrayListOf<MovieResponse>()

    items.add(
        MovieResponse(
            movieId = "tt4857886",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/9/9a/Do_Lafzon_Ki_Kahani_Poster.jpg/220px-Do_Lafzon_Ki_Kahani_Poster.jpg",
            title = "Do lafzon ki Kahani",
            wikiLink = "https://en.wikipedia.org/wiki/Aag_Ka_Gola"
        )
    )
    items.add(
        MovieResponse(
            movieId = "tt4857886",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/9/91/Udta_Punjab.jpg/220px-Udta_Punjab.jpg",
            title = "Udta Punjab",
            wikiLink = "https://en.wikipedia.org/wiki/Aag_Ka_Gola"
        )
    )
    items.add(
        MovieResponse(
            movieId = "tt4814290",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/2/20/Te3n_official_poster.jpg/220px-Te3n_official_poster.jpg",
            title = "Te3n",
            wikiLink = "https://en.wikipedia.org/wiki/Aag_Ka_Gola"
        )
    )
    items.add(
        MovieResponse(
            movieId = "tt4857886",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/9/9a/Do_Lafzon_Ki_Kahani_Poster.jpg/220px-Do_Lafzon_Ki_Kahani_Poster.jpg",
            title = "Aag Ka Gola",
            wikiLink = "https://en.wikipedia.org/wiki/Aag_Ka_Gola"
        )
    )
    items.add(
        MovieResponse(
            movieId = "tt4857886",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/2/20/Te3n_official_poster.jpg/220px-Te3n_official_poster.jpg",
            title = "Aag Ka Gola",
            wikiLink = "https://en.wikipedia.org/wiki/Aag_Ka_Gola"
        )
    )
    items.add(
        MovieResponse(
            movieId = "tt4814290",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/9/9a/Do_Lafzon_Ki_Kahani_Poster.jpg/220px-Do_Lafzon_Ki_Kahani_Poster.jpg",
            title = "Aag Ka Gola",
            wikiLink = "https://en.wikipedia.org/wiki/Aag_Ka_Gola"
        )
    )
    items.add(
        MovieResponse(
            movieId = "tt4857886",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/9/9a/Do_Lafzon_Ki_Kahani_Poster.jpg/220px-Do_Lafzon_Ki_Kahani_Poster.jpg",
            title = "Aag Ka Gola",
            wikiLink = "https://en.wikipedia.org/wiki/Aag_Ka_Gola"
        )
    )
    items.add(
        MovieResponse(
            movieId = "tt4434004",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/9/91/Udta_Punjab.jpg/220px-Udta_Punjab.jpg",
            title = "Udta Punjab",
            wikiLink = "https://en.wikipedia.org/wiki/Udta_Punjab"
        )
    )
    items.add(
        MovieResponse(
            movieId = "tt4814290",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/2/20/Te3n_official_poster.jpg/220px-Te3n_official_poster.jpg",
            title = "Te3n",
            wikiLink = "https://en.wikipedia.org/wiki/Aag_Ka_Gola"
        )
    )
    return items
}
