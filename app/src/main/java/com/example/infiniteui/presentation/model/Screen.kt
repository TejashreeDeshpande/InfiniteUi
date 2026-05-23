package com.example.infiniteui.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraRoll
import androidx.compose.material.icons.filled.Folder
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable data object Feed : Screen()
    @Serializable data object Gallery : Screen()
}

// These extensions are completely invisible to the serializer!
val Screen.title: String
    get() = when (this) {
        Screen.Feed -> "Feed"
        Screen.Gallery -> "Gallery"
    }

val Screen.icon: ImageVector
    get() = when (this) {
        Screen.Feed -> Icons.Default.Folder
        Screen.Gallery -> Icons.Default.CameraRoll
    }
