package dev.ikti.khanza.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun HomeDropdownLabel() {
    Text(
        text = "Pilih menu",
        style = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            fontFamily = FontGilroy
        )
    )
}

@Preview
@Composable
fun HomeDropdownLabeLPreview() {
    KhanzaTheme {
        HomeDropdownLabel()
    }
}
