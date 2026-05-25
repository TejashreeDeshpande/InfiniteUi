package com.example.infiniteui.presentation.feed.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.infiniteui.data.Article
import com.example.infiniteui.presentation.common.components.EmptyState
import com.example.infiniteui.presentation.common.components.ErrorState
import com.example.infiniteui.presentation.feed.viewmodel.ArticleViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListScreen() {
    val viewModel: ArticleViewModel = koinViewModel()
    val articles = viewModel.articles.collectAsLazyPagingItems()

    val isRefreshing = articles.loadState.refresh is LoadState.Loading
    val pullToRefreshState = rememberPullToRefreshState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .background(
                                    brush = Brush.linearGradient(
                                        listOf(
                                            MaterialTheme.colorScheme.primary,
                                            MaterialTheme.colorScheme.secondary
                                        )
                                    ),
                                    shape = RoundedCornerShape(14.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "∞",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }

                        Column {
                            Text(
                                text = "Infinite Scroll",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = "Powered by Paging 3",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when {
                articles.loadState.refresh is LoadState.Loading && articles.itemCount == 0 -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }

                articles.loadState.refresh is LoadState.Error -> {
                    val error = articles.loadState.refresh as LoadState.Error
                    ErrorState(
                        errorMessage = error.error.localizedMessage ?: "Unknown error occurred",
                        onRetry = { articles.retry() }
                    )
                }

                articles.loadState.refresh is LoadState.NotLoading && articles.itemCount == 0 -> {
                    EmptyState()
                }

                else -> {
                    PullToRefreshBox(
                        isRefreshing = isRefreshing,
                        onRefresh = { articles.refresh() },
                        state = pullToRefreshState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 16.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(
                                count = articles.itemCount,
                                key = articles.itemKey { it.id }
                            ) { index ->
                                val article = articles[index]

                                if (article != null) {
                                    ArticleCard(article)
                                }
                            }
                            when (articles.loadState.append) {
                                is LoadState.Loading -> {
                                    item {
                                        CircularProgressIndicator(
                                            color = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp)
                                                .wrapContentWidth()
                                        )
                                    }
                                }

                                is LoadState.Error -> {
                                    item {
                                        Button(
                                            onClick = { articles.retry() },
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Text("Retry")
                                        }
                                    }
                                }

                                else -> Unit
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ArticleCard(article: Article) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = article.category,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = article.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = article.subtitle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2
            )
            Spacer(Modifier.height(10.dp))
            AssistChip(
                onClick = {},
                label = {
                    Text(
                        text = article.readTime,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                ),
                border = null
            )
        }
    }
}
