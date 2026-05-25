package com.example.infiniteui.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.infiniteui.data.Article
import com.example.infiniteui.data.MockRepository
import kotlinx.coroutines.delay

class ArticlePagingSource(
    private val loadDelay: Long = 800L
) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val allArticles = MockRepository.mockArticles

        return try {
            if (loadDelay > 0) {
                delay(loadDelay)
            }

            val page = params.key ?: 0
            val pageSize = params.loadSize
            val fromIndex = page * pageSize
            val toIndex = minOf(fromIndex + pageSize, allArticles.size)

            val items = if (fromIndex < allArticles.size) {
                allArticles.subList(fromIndex, toIndex)
            } else emptyList()

            LoadResult.Page(
                data = items,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (toIndex >= allArticles.size) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}
