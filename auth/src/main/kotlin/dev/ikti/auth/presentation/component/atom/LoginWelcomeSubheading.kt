package dev.ikti.auth.presentation.component.atom

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
fun LoginWelcomeSubheading() {
    Text(
        text = "Silakan masuk ke akun Anda",
        color = Color(0xFF535353),
        style = TextStyle(
            color = Color(0xFF272727),
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            fontFamily = FontGilroy
        )
    )
}

@Preview
@Composable
fun LoginWelcomeSubheadingPreview() {
    OMNIATheme {
        LoginWelcomeSubheading()
    }
}