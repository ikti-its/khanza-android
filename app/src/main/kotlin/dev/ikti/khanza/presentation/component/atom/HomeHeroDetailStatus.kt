package dev.ikti.khanza.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun HomeHeroDetailStatus(status: Boolean = false) {
    Text(
        text = if (status) "HADIR" else "BELUM HADIR",
        color = Color(0xFFACF2E7),
        style = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            fontFamily = FontGilroy
        )
    )
}

@Preview
@Composable
fun HomeHeroDetailStatusTruePreview() {
    KhanzaTheme {
        HomeHeroDetailStatus(true)
    }
}

@Preview
@Composable
fun HomeHeroDetailStatusFalsePreview() {
    KhanzaTheme {
        HomeHeroDetailStatus(false)
    }
}