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
fun HomeHeroDetailShift(masuk: String = "08:00", pulang: String = "16:00") {
    Text(
        text = "$masuk - $pulang",
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
fun HomeHeroDetailShiftPreview() {
    KhanzaTheme {
        HomeHeroDetailShift()
    }
}