package com.example.fintrack.ui.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = Color.Black,
    secondary = Color.DarkGray,
    background = Color.Black,
    onBackground = Color.White,
    onSurface = Color.Gray,
    onSurfaceVariant = Color.LightGray,
    error = Color.Red
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Black,
    secondary = Color.Gray,
    background = Color.White,
    onBackground = Color.Black,
    onSurface = Color.DarkGray,
    onSurfaceVariant = Color.Gray,
    error = Color.Red
)

@Composable
fun FinanceAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme
    val spacing = Spacing()

    CompositionLocalProvider(LocalSpacing provides spacing) {
        MaterialTheme(
            colorScheme = colors,
            typography = MaterialTheme.typography,
            content = content
        )
    }
}

object MaterialThemeSpacing {
    val spacing: Spacing
        @Composable
        get() = LocalSpacing.current
}

class Spacing {
    val extraSmall = 4.dp
    val small = 8.dp
    val medium = 16.dp
}

val LocalSpacing = compositionLocalOf { Spacing() }
