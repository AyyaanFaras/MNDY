package com.devs.mndy.utils.colors

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val LightColorScheme = MndyColors(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    background = LightBackground,
    onBackground = LightOnBackground,
)

private val DarkColorScheme = MndyColors(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    background = DarkBackground,
    onBackground = DarkOnBackground,
)

private val LocalMndyColors = staticCompositionLocalOf { LightColorScheme }


object mndyTheme {
    val colors: MndyColors
        @Composable
        get() = LocalMndyColors.current
}


@Composable
fun MndyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
        LocalMndyColors provides colors,
        content = content
    )
}