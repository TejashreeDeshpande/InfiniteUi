package com.example.infiniteui.di

import com.example.infiniteui.data.ArticleRepository
import com.example.infiniteui.data.ImageRepository
import com.example.infiniteui.presentation.feed.viewmodel.ArticleViewModel
import com.example.infiniteui.presentation.ImageViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ArticleRepository() }
    single { ImageRepository() }
    viewModel { ArticleViewModel(get()) }
    viewModel { ImageViewModel(get()) }
}
