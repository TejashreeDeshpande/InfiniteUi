package com.example.infiniteui.presentation.feed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.infiniteui.presentation.feed.pagingsource.ArticlePagingSource

class ArticleViewModel() : ViewModel() {
    var articles = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ArticlePagingSource() }
    ).flow.cachedIn(viewModelScope)

}