package com.odroid.movieready.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchlistDao {
    @Query("SELECT * FROM watchlist_movies ORDER BY time_stamp DESC")
    fun getAllMovies(): Flow<List<TmdbMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovieToWatchList(tmdbMovie: TmdbMovie)

    @Query("DELETE FROM watchlist_movies WHERE id = :movieId")
    fun removeMovieFromWatchlist(movieId: Long)
}