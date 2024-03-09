package dev.ikti.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Temporary
private val LightColorScheme = lightColorScheme(
    primary = Khanza500,
    onPrimary = KhanzaOnPrimary,
    secondary = Khanza900,
)

@Composable
fun KhanzaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        shapes = shapes,
        typography = typography,
        content = content
    )
}
