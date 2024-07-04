package dev.ikti.home.presentation.component.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.OMNIATheme

@Composable
fun HomeHeroDetailStatusIndicator(status: Boolean = false) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .width(8.dp)
            .height(8.dp)
            .background(color = if (status) Color(0xFF83ECDC) else Color(0xFFEF4444))
            .border(
                width = 1.dp,
                color = Color(0xFF26B29D).copy(0.5f),
                shape = RoundedCornerShape(30.dp)
            )
    ) {}
}

@Preview
@Composable
fun HomeHeroDetailStatusIndicatorFalsePreview() {
    OMNIATheme {
        HomeHeroDetailStatusIndicator(false)
    }
}

@Preview
@Composable
fun HomeHeroDetailStatusIndicatorTruePreview() {
    OMNIATheme {
        HomeHeroDetailStatusIndicator(true)
    }
}
