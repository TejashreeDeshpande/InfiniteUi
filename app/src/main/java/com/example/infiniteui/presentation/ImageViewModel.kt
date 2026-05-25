package com.example.infiniteui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.infiniteui.data.ImageRepository

class ImageViewModel(
    repository: ImageRepository
) : ViewModel() {
    val images = repository.getImages().cachedIn(viewModelScope)
}
