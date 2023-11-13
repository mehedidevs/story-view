package com.example.my_stories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

class ImagePagingSource(private val allResources: List<List<Int>>) :
    PagingSource<Int, Int>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Int> {
        return try {
            val currentPage = params.key ?: 0
            val data = allResources.getOrNull(currentPage) ?: emptyList()

            LoadResult.Page(
                data = data,
                prevKey = if (currentPage > 0) currentPage - 1 else null,
                nextKey = if (currentPage < allResources.size - 1) currentPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Int>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}