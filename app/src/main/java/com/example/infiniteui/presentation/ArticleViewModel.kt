package com.example.infiniteui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class ArticleViewModel() : ViewModel() {
    var articles = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ArticlePagingSource() }
    ).flow.cachedIn(viewModelScope)

}