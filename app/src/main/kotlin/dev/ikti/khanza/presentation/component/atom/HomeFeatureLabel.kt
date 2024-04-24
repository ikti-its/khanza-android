package dev.ikti.khanza.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontPlusJakartaSans
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun HomeFeatureLabel() {
    Text(
        text = "Aplikasi",
        style = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp,
            fontFamily = FontPlusJakartaSans
        )
    )
}

@Preview
@Composable
fun HomeFeatureLabelPreview() {
    KhanzaTheme {
        HomeFeatureLabel()
    }
}