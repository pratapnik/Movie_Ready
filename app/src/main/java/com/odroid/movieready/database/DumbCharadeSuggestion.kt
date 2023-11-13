package com.odroid.movieready.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dumb_charades_suggestions")
data class DumbCharadeSuggestion(
    @PrimaryKey
    var id: Long,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "release_date")
    var releaseDate: String,
    @ColumnInfo(name = "poster_path")
    var posterPath: String,
    @ColumnInfo(name = "avg_rating")
    var avgRating: Float,
    @ColumnInfo(name = "rating_count")
    var ratingCount: Long,
    @ColumnInfo(name = "time_stamp")
    val timeStamp: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "adult")
    val isAdult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    var backDropPath: String,
    @ColumnInfo(name = "genre_ids")
    var genreIds: String,
    @ColumnInfo(name = "popularity")
    var popularity: Double,
)