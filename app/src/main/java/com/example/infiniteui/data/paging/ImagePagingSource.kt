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

            val page = params.key ?: 0
            val pageSize = params.loadSize
            val fromIndex = page * pageSize
            val toIndex = minOf(fromIndex + pageSize, allImages.size)

            val items = if (fromIndex < allImages.size) {
                allImages.subList(fromIndex, toIndex)
            } else emptyList()

            LoadResult.Page(
                data = items,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (toIndex >= allImages.size) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageItem>): Int? {
        return state.anchorPosition?.let { position ->

            state.closestPageToPosition(position)
                ?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)
                    ?.nextKey?.minus(1)
        }
    }
}