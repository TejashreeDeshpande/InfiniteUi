package com.example.infiniteui.presentation.gallery.components

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

import androidx.paging.compose.itemKey

import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import com.example.infiniteui.presentation.common.modifiers.lazyStaggeredGridScrollbar

@Composable
fun StaggeredImageGridView(
    images: LazyPagingItems<ImageItem>,
    onImageClick: (ImageItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = rememberLazyStaggeredGridState()
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .lazyStaggeredGridScrollbar(state),
        state = state,
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 12.dp
    ) {
        items(
            count = images.itemCount,
            key = images.itemKey { it.id },
            contentType = { "image_card" }
        ) { index ->

            val image = images[index]
            image?.let {
                StaggeredImageCard(
                    image = it,
                    onClick = { onImageClick(it) })
            }
        }
    }
}