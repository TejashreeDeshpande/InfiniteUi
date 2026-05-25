package com.example.infiniteui

import android.app.Application
import com.example.infiniteui.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InfiniteUiApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@InfiniteUiApp)
            modules(appModule)
        }
    }
}