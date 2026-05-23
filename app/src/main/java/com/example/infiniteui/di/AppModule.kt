package com.example.infiniteui.di

import com.example.infiniteui.presentation.feed.viewmodel.ArticleViewModel
import com.example.infiniteui.presentation.gallery.viewmodel.ImageViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ArticleViewModel() }
    viewModel { ImageViewModel() }
}
