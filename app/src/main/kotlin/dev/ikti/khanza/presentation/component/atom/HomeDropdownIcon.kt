package dev.ikti.khanza.presentation.component.atom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun HomeDropdownIcon() {
    Icon(
        Icons.Rounded.KeyboardArrowDown,
        contentDescription = null
    )
}

@Preview
@Composable
fun HomeDropdownIconPreview() {
    KhanzaTheme {
        HomeDropdownIcon()
    }
}
