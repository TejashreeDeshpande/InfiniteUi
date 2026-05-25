package com.example.infiniteui.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.infiniteui.data.paging.ArticlePagingSource
import kotlinx.coroutines.flow.Flow

class ArticleRepository {
    fun getArticles(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ArticlePagingSource() }
        ).flow
    }
}
