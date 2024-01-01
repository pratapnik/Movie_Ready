package com.odroid.movieready.repository

import com.odroid.movieready.base.IshaaraApplication
import com.odroid.movieready.database.AppDatabase
import com.odroid.movieready.database.TmdbMovie
import com.odroid.movieready.entity.MovieDetail
import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class TmdbMovieRepositoryImpl : TmdbMovieRepository {

    private val appDatabase = IshaaraApplication.context?.applicationContext?.let {
        AppDatabase.getDatabase(
            it
        )
    }

    override suspend fun getPopularMovies(page: Int): List<TmdbItem> {
        return RetrofitBuilder.tmdbMovieApiService.getPopularMovies(pageNumber = page).moviesList
    }

    override suspend fun getUpcomingMovies(page: Int): List<TmdbItem> {
        return RetrofitBuilder.tmdbMovieApiService.getUpcomingMovies(pageNumber = page).moviesList
    }

    override suspend fun getTopRatedMovies(page: Int): List<TmdbItem> {
        return RetrofitBuilder.tmdbMovieApiService.getTopRatedMovies(pageNumber = page).moviesList
    }

    override suspend fun getNowPlayingMovies(page: Int): List<TmdbItem> {
        return RetrofitBuilder.tmdbMovieApiService.getNowPlayingMovies(pageNumber = page).moviesList
    }

    override suspend fun getMovieDetails(movieId: Long): Flow<MovieDetail?> {
        return flow {
            emit(RetrofitBuilder.tmdbMovieApiService.getMovieDetails(movieId = movieId).body())
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun addToWatchlist(tmdbItem: TmdbItem) {
        appDatabase?.let {
            tmdbItem.id?.run {
                val tmdbMovie = TmdbMovie(
                    this,
                    tmdbItem.title ?: "",
                    tmdbItem.description?: "",
                    tmdbItem.releaseDate?: "",
                    tmdbItem.posterUrl?: "",
                    tmdbItem.avgRating?: 0F,
                    tmdbItem.ratingCount?: 0L
                )
                it.watchlistDao().addMovieToWatchList(tmdbMovie)
            }
        }
    }

    override suspend fun removeMovieFromWatchlist(movieId: Long) {
        appDatabase?.watchlistDao()?.removeMovieFromWatchlist(movieId)
    }

    override fun getWatchlistMovies(): Flow<List<TmdbItem>> {
        val tmdbItemList = arrayListOf<TmdbItem>()
        return appDatabase?.watchlistDao()?.getAllMovies()?.map {
            for (item in it) {
                tmdbItemList.add(
                    TmdbItem(
                        item.id,
                        item.title,
                        item.description,
                        item.releaseDate,
                        item.posterPath,
                        item.avgRating,
                        item.ratingCount
                    )
                )
            }
            tmdbItemList
        }?.flowOn(Dispatchers.IO) ?: emptyFlow()
    }
}