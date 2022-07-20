package com.odroid.movieready.view_intent

import com.odroid.movieready.R
import com.odroid.movieready.entity.MovieResponse

sealed class CategoryWithList(
    var title: String = "",
    var icon: Int = 0,
    var list: ArrayList<MovieResponse> = arrayListOf(),
    val category: Category
) {

    object Trending :
        CategoryWithList("Trending", R.drawable.hot, getMoviesList(), Category.TRENDING)

    object NowPlaying : CategoryWithList(
        "Now Playing",
        R.drawable.ic_play_button,
        getMoviesList(),
        Category.NOW_PLAYING
    )

    object TopRated :
        CategoryWithList("Top Rated", R.drawable.ic_rating, getMoviesList(), Category.TOP_RATED)

    object Popular : CategoryWithList("Popular", R.drawable.hot, getPopularMoviesList(), Category.POPULAR)
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

fun getPopularMoviesList(): ArrayList<MovieResponse> {
    val items = arrayListOf<MovieResponse>()

    items.add(
        MovieResponse(
            movieId = "tt4857886",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/9/9a/Do_Lafzon_Ki_Kahani_Poster.jpg/220px-Do_Lafzon_Ki_Kahani_Poster.jpg",
            title = "Aag ka Gola",
            wikiLink = "https://en.wikipedia.org/wiki/Aag_Ka_Gola"
        )
    )
    items.add(
        MovieResponse(
            movieId = "tt4857886",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/9/91/Udta_Punjab.jpg/220px-Udta_Punjab.jpg",
            title = "Udta Punjab ti.ll the end",
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

enum class Category {
    POPULAR,
    TOP_RATED,
    TRENDING,
    NOW_PLAYING
}

