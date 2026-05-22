package com.example.infiniteui.di

import com.example.infiniteui.presentation.ArticleViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ArticleViewModel() }
}
