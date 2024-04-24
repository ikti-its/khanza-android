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
fun HomeFeatureItemLabel(label: String) {
    Text(
        text = label,
        color = Color(0xFF0C203C),
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
            fontFamily = FontGilroy
        )
    )
}

@Preview
@Composable
fun HomeFeatureItemLabelPreview() {
    KhanzaTheme {
        HomeFeatureItemLabel(label = "DEV")
    }
}