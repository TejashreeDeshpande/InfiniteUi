package com.example.infiniteui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.infiniteui.presentation.MainScreen
import com.example.infiniteui.ui.theme.InfiniteUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfiniteUITheme {
                MainScreen()
            }
        }
    }
}