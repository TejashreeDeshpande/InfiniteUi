package com.example.infiniteui.presentation.gridview.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.infiniteui.data.ImageItem

@Composable
fun StaggeredImageCard(
    image: ImageItem,
    modifier: Modifier = Modifier,
    onClick: (ImageItem) -> Unit
) {
    // We can keep the random height for a "masonry" look even in a regular grid, 
    // or set a fixed height for a standard grid look.
    val height = remember(image.id) {
        listOf(180.dp, 220.dp, 260.dp).random()
    }
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = {
            onClick(image)
        }
    ) {
        AsyncImage(
            model = image.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
