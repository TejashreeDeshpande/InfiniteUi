package com.example.infiniteui.presentation.common.modifiers

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.lazyListScrollbar(
    state: LazyListState,
    width: Dp = 4.dp,
    color: Color? = null
): Modifier = composed {
    val targetColor = color ?: MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
    val alpha by animateFloatAsState(
        targetValue = if (state.isScrollInProgress) 1f else 0f,
        animationSpec = tween(durationMillis = if (state.isScrollInProgress) 100 else 500),
        label = "scrollbar_alpha"
    )

    drawWithContent {
        drawContent()
        val layoutInfo = state.layoutInfo
        val totalItemsCount = layoutInfo.totalItemsCount
        val visibleItemsCount = layoutInfo.visibleItemsInfo.size

        if (totalItemsCount > 0 && visibleItemsCount < totalItemsCount) {
            val scrollbarHeight = size.height
            val thumbHeight = (visibleItemsCount.toFloat() / totalItemsCount) * scrollbarHeight
            val thumbOffset = (state.firstVisibleItemIndex.toFloat() / totalItemsCount) * scrollbarHeight

            drawRoundRect(
                color = targetColor.copy(alpha = targetColor.alpha * alpha),
                topLeft = Offset(size.width - width.toPx() - 4.dp.toPx(), thumbOffset),
                size = Size(width.toPx(), thumbHeight),
                cornerRadius = CornerRadius(width.toPx() / 2, width.toPx() / 2)
            )
        }
    }
}

fun Modifier.lazyGridScrollbar(
    state: LazyGridState,
    width: Dp = 4.dp,
    color: Color? = null
): Modifier = composed {
    val targetColor = color ?: MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
    val alpha by animateFloatAsState(
        targetValue = if (state.isScrollInProgress) 1f else 0f,
        animationSpec = tween(durationMillis = if (state.isScrollInProgress) 100 else 500),
        label = "scrollbar_alpha"
    )

    drawWithContent {
        drawContent()
        val layoutInfo = state.layoutInfo
        val totalItemsCount = layoutInfo.totalItemsCount
        val visibleItemsCount = layoutInfo.visibleItemsInfo.size

        if (totalItemsCount > 0 && visibleItemsCount < totalItemsCount) {
            val scrollbarHeight = size.height
            val thumbHeight = (visibleItemsCount.toFloat() / totalItemsCount) * scrollbarHeight
            val thumbOffset = (state.firstVisibleItemIndex.toFloat() / totalItemsCount) * scrollbarHeight

            drawRoundRect(
                color = targetColor.copy(alpha = targetColor.alpha * alpha),
                topLeft = Offset(size.width - width.toPx() - 4.dp.toPx(), thumbOffset),
                size = Size(width.toPx(), thumbHeight),
                cornerRadius = CornerRadius(width.toPx() / 2, width.toPx() / 2)
            )
        }
    }
}

fun Modifier.lazyStaggeredGridScrollbar(
    state: LazyStaggeredGridState,
    width: Dp = 4.dp,
    color: Color? = null
): Modifier = composed {
    val targetColor = color ?: MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
    val alpha by animateFloatAsState(
        targetValue = if (state.isScrollInProgress) 1f else 0f,
        animationSpec = tween(durationMillis = if (state.isScrollInProgress) 100 else 500),
        label = "scrollbar_alpha"
    )

    drawWithContent {
        drawContent()
        val layoutInfo = state.layoutInfo
        val totalItemsCount = layoutInfo.totalItemsCount
        val visibleItemsCount = layoutInfo.visibleItemsInfo.size

        if (totalItemsCount > 0 && visibleItemsCount < totalItemsCount) {
            val scrollbarHeight = size.height
            val thumbHeight = (visibleItemsCount.toFloat() / totalItemsCount) * scrollbarHeight
            val thumbOffset = (state.firstVisibleItemIndex.toFloat() / totalItemsCount) * scrollbarHeight

            drawRoundRect(
                color = targetColor.copy(alpha = targetColor.alpha * alpha),
                topLeft = Offset(size.width - width.toPx() - 4.dp.toPx(), thumbOffset),
                size = Size(width.toPx(), thumbHeight),
                cornerRadius = CornerRadius(width.toPx() / 2, width.toPx() / 2)
            )
        }
    }
}
