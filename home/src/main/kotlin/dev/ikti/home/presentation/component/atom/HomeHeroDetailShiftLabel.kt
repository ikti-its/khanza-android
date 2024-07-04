package dev.ikti.home.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.presentation.theme.OMNIATheme

@Composable
fun HomeHeroDetailShiftLabel() {
    Text(
        text = "Shift",
        color = Color(0xFFACF2E7),
        style = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            fontFamily = FontGilroy
        )
    )
}

@Preview
@Composable
fun HomeHeroDetailShiftLabelPreview() {
    OMNIATheme {
        HomeHeroDetailShiftLabel()
    }
}