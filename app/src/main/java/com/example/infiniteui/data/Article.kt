package com.example.infiniteui.data

import androidx.compose.runtime.Immutable

@Immutable
data class Article(
    val id: Int,
    val title: String,
    val subtitle: String,
    val category: String,
    val readTime: String,
)