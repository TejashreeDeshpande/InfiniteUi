package com.example.infiniteui.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = PurplePrimary,
    secondary = PurpleSecondary,

    background = Background,
    surface = Surface,
    surfaceVariant = SurfaceSoft,

    onPrimary = Color.White,
    onSecondary = Color.White,

    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,

    outline = BorderLight,

    error = ErrorRed,
    onError = Color.White
)

private val DarkColors = darkColorScheme(
    primary = PurpleSecondary,
    secondary = PurpleLight,

    background = DarkBackground,
    surface = DarkSurface,
    surfaceVariant = DarkSurfaceSoft,

    onPrimary = Color.White,
    onSecondary = Color.White,

    onBackground = DarkTextPrimary,
    onSurface = DarkTextPrimary,
    onSurfaceVariant = DarkTextSecondary,

    outline = DarkBorder,

    error = ErrorRed,
    onError = Color.White
)

@Composable
fun InfiniteUITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val color = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = color,
        typography = Typography,
        content = content
    )
}