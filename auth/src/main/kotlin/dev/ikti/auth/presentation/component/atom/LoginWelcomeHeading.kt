package dev.ikti.auth.presentation.component.atom

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
fun LoginWelcomeHeading() {
    Text(
        text = "Selamat datang kembali!",
        color = Color(0xFF272727),
        style = TextStyle(
            color = Color(0xFF272727),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            fontFamily = FontGilroy
        )
    )
}

@Preview
@Composable
fun LoginWelcomeHeadingPreview() {
    KhanzaTheme {
        LoginWelcomeHeading()
    }
}