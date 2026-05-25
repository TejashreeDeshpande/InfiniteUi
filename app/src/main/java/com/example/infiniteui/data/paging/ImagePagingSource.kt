package com.example.infiniteui.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.infiniteui.data.ImageItem
import com.example.infiniteui.data.MockRepository
import kotlinx.coroutines.delay

class ImagePagingSource: PagingSource<Int, ImageItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageItem> {
        val allImages = MockRepository.allImages

        return try {
            delay(800)

            val position = params.key ?: 0
            val loadSize = params.loadSize
            
            val toIndex = minOf(position + loadSize, allImages.size)

            val items = if (position < allImages.size) {
                allImages.subList(position, toIndex)
            } else emptyList()

            LoadResult.Page(
                data = items,
                prevKey = if (position == 0) null else maxOf(0, position - loadSize),
                nextKey = if (toIndex >= allImages.size) null else toIndex
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageItem>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)
                ?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(position)
                    ?.nextKey?.minus(state.config.pageSize)
        }
    }
}