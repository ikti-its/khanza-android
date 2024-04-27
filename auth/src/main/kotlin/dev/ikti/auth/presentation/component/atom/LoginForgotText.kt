package dev.ikti.auth.presentation.component.atom

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun LoginForgotText(modifier: Modifier = Modifier) {
    Text(
        "Lupa kata sandi?",
        modifier = modifier.clickable { },
        style = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            fontFamily = FontGilroy,
            textDecoration = TextDecoration.Underline
        )
    )
}

@Preview
@Composable
fun LoginForgotTextPreview() {
    KhanzaTheme {
        LoginForgotText()
    }
}