package com.example.infiniteui.presentation.gallery.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.infiniteui.data.ImageItem
import com.example.infiniteui.presentation.gallery.viewmodel.ImageViewModel
import org.koin.compose.viewmodel.koinViewModel

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
                        RetryItem(
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            images.retry()
                        }
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

@Composable
fun RetryItem(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text("Something went wrong")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}