package com.odroid.movieready.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.odroid.movieready.entity.TmdbMovie
import com.odroid.movieready.repository.TmdbMovieRepository

class TmdbLatestMoviesSource(private val tmdbMovieRepository: TmdbMovieRepository) :
    PagingSource<Int, TmdbMovie>() {

    override fun getRefreshKey(state: PagingState<Int, TmdbMovie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TmdbMovie> {
        return try {
            val page = params.key ?: 1
            val latestMovies = tmdbMovieRepository.getLatestMovies(page)
            LoadResult.Page(
                data = latestMovies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}