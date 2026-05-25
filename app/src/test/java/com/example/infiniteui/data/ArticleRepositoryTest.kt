package com.example.infiniteui.data

import androidx.paging.testing.asSnapshot
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ArticleRepositoryTest {

    private val repository = ArticleRepository()

    @Test
    fun `getArticles returns paging data with articles`() = runTest {
        val items = repository.getArticles().asSnapshot {
            // No need to scroll if we just want the first page
        }

        // MockRepository has 100 articles, first page size is 10 (initial load is 3 * pageSize = 30 by default in Pager)
        // Let's check if we got at least some articles
        assertTrue(items.isNotEmpty())
        assertEquals(MockRepository.mockArticles.first().title, items.first().title)
    }
}
