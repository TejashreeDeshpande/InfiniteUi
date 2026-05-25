package com.example.infiniteui.presentation.feed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.infiniteui.data.ArticleRepository

class ArticleViewModel(
    repository: ArticleRepository
) : ViewModel() {
    val articles = repository.getArticles().cachedIn(viewModelScope)
}
