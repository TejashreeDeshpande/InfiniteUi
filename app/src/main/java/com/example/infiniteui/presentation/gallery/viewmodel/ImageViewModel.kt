package com.example.infiniteui.presentation.gallery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.infiniteui.presentation.gallery.pagingsource.ImagePagingSource

class ImageViewModel : ViewModel() {
    var images = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ImagePagingSource() }
    ).flow.cachedIn(viewModelScope)
}