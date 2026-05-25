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

            val position = params.key ?: 0
            val loadSize = params.loadSize
            
            val toIndex = minOf(position + loadSize, allArticles.size)

            val items = if (position < allArticles.size) {
                allArticles.subList(position, toIndex)
            } else emptyList()

            LoadResult.Page(
                data = items,
                prevKey = if (position == 0) null else maxOf(0, position - loadSize),
                nextKey = if (toIndex >= allArticles.size) null else toIndex
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(state.config.pageSize)
        }
    }
}
