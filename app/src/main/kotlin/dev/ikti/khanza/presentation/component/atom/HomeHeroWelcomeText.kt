package dev.ikti.khanza.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.R

@Composable
fun HomeHeroWelcomeText() {
    Text(
        text = stringResource(id = R.string.home_welcome),
        color = Color(0xFF0A2D27),
        style = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            fontFamily = FontGilroy
        )
    )
}

@Preview
@Composable
fun HomeHeroWelcomeTextPreview() {
    KhanzaTheme {
        HomeHeroWelcomeText()
    }
}