package com.odroid.movieready.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.odroid.movieready.entity.TmdbMovie
import com.odroid.movieready.repository.TmdbMovieRepository

class PopularMoviesSource(private val tmdbMovieRepository: TmdbMovieRepository) :
    PagingSource<Int, TmdbMovie>() {

    override fun getRefreshKey(state: PagingState<Int, TmdbMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TmdbMovie> {
        return try {
            val page = params.key ?: 1
            val latestMovies = tmdbMovieRepository.getPopularMovies(page)
            Log.d("NPS", "pageNo: $page, latestMovies:$latestMovies ,latestMoviesSize: ${latestMovies.size}")
            return if (latestMovies.isEmpty()) {
                LoadResult.Error(
                    Exception("")
                )
            } else {
                LoadResult.Page(
                    data = latestMovies,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = page.plus(1)
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}