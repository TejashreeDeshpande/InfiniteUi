package com.example.infiniteui.presentation.gridview.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.infiniteui.data.ImageItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun StaggeredImageGridView(
    images: LazyPagingItems<ImageItem>,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onImageClick: (ImageItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 12.dp
    ) {
        items(images.itemCount) { index ->
            val image = images[index]
            image?.let { item ->
                with(sharedTransitionScope) {
                    StaggeredImageCard(
                        image = item,
                        modifier = Modifier.sharedElement(
                            rememberSharedContentState(key = "image-${item.id}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                        onClick = onImageClick
                    )
                }
            }
        }
    }
}
