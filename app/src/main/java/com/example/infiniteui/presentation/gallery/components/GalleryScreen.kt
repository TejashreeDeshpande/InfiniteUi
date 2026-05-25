package com.example.infiniteui.presentation.gallery.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.infiniteui.data.ImageItem
import com.example.infiniteui.presentation.ImageViewModel
import com.example.infiniteui.presentation.gridview.components.HeroImageScreen
import org.koin.compose.viewmodel.koinViewModel

import com.example.infiniteui.presentation.common.components.ErrorState

@Composable
fun GalleryScreen() {

    val viewModel: ImageViewModel = koinViewModel()
    val images = viewModel.images.collectAsLazyPagingItems()
    var selectedImage by remember {
        mutableStateOf<ImageItem?>(null)
    }

    AnimatedContent(
        targetState = selectedImage,
        label = "heroTransition"
    ) { image ->
        if (image == null) {
            Box(modifier = Modifier.fillMaxSize()) {
                when (images.loadState.refresh) {
                    is LoadState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    is LoadState.Error -> {
                        val error = images.loadState.refresh as LoadState.Error
                        ErrorState(
                            modifier = Modifier.align(Alignment.Center),
                            errorMessage = error.error.localizedMessage ?: "Unknown error occurred",
                            onRetry = { images.retry() }
                        )
                    }

                    else -> StaggeredImageGridView(
                        images = images,
                        onImageClick = {
                            selectedImage = it
                        })
                }
            }

        } else {
            HeroImageScreen(
                image = image,
                onBack = {
                    selectedImage = null
                }
            )
        }
    }
}
