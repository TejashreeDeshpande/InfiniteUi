package com.example.infiniteui.data.paging

import androidx.paging.PagingSource
import com.example.infiniteui.data.MockRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ArticlePagingSourceTest {

    @Test
    fun `load returns success page when data is available`() = runTest {
        val pagingSource = ArticlePagingSource(loadDelay = 0L)
        
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Page)
        val page = result as PagingSource.LoadResult.Page
        assertEquals(10, page.data.size)
        assertEquals(MockRepository.mockArticles.take(10), page.data)
    }

    @Test
    fun `load returns empty list when out of bounds`() = runTest {
        val pagingSource = ArticlePagingSource(loadDelay = 0L)
        
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 20, // 20 * 10 = 200, which is > 100 articles
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Page)
        val page = result as PagingSource.LoadResult.Page
        assertTrue(page.data.isEmpty())
    }
}
