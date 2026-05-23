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

@Composable
fun StaggeredImageGridView(
    images: LazyPagingItems<ImageItem>,
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
        items(
            count = images.itemCount,
            key = { index -> images[index]?.id ?: index }
        ) { index ->

            val image = images[index]
            image?.let {
                StaggeredImageCard(
                    image = it,
                    onClick = { onImageClick(image) })
            }
        }
    }
}