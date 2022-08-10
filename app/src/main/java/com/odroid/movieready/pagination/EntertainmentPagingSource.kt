package com.odroid.movieready.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.odroid.movieready.entity.SourceType
import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.repository.TmdbMovieRepository

class EntertainmentPagingSource(
    private val tmdbMovieRepository: TmdbMovieRepository,
    private val sourceType: SourceType
) :
    PagingSource<Int, TmdbItem>() {

    override fun getRefreshKey(state: PagingState<Int, TmdbItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TmdbItem> {
        return try {
            val page = params.key ?: 1
            val dataFromRemote = when (sourceType) {
                SourceType.POPULAR_MOVIES -> tmdbMovieRepository.getPopularMovies(page)
                SourceType.TRENDING_MOVIES -> {
                    tmdbMovieRepository.getPopularMovies(page)
                }
                SourceType.LATEST_MOVIES -> tmdbMovieRepository.getLatestMovies(page)
            }

            Log.d(
                "NPS",
                "pageNo: $page, latestMovies:$dataFromRemote ,latestMoviesSize: ${dataFromRemote.size}"
            )
            return if (dataFromRemote.isEmpty()) {
                LoadResult.Error(
                    Exception("")
                )
            } else {
                LoadResult.Page(
                    data = dataFromRemote,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = page.plus(1)
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}