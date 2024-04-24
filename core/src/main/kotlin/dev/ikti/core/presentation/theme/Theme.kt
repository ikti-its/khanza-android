package dev.ikti.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Temporary
private val LightColorScheme = lightColorScheme(
    primary = Union200,
    onPrimary = KhanzaOnPrimary,
    secondary = Union900,
)

@Composable
fun KhanzaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = typography,
        content = content
    )
}
