package dev.ikti.home.presentation.component.atom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun HomeDropdownIcon() {
    Icon(
        Icons.Rounded.KeyboardArrowDown,
        contentDescription = null,
        tint = Color(0xFF0A2D27)
    )
}

@Preview
@Composable
fun HomeDropdownIconPreview() {
    KhanzaTheme {
        HomeDropdownIcon()
    }
}
