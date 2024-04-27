package dev.ikti.onboarding.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun OnboardingButtonLabel(selectedIndex: Int) {
    if (selectedIndex != 2) {
        Text(
            text = "Selanjutnya",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = FontGilroy
            )
        )
    } else {
        Text(
            text = "Masuk",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = FontGilroy
            )
        )
    }
}

@Preview
@Composable
fun OnboardingButtonLabelNextPreview() {
    KhanzaTheme {
        OnboardingButtonLabel(0)
    }
}

@Preview
@Composable
fun OnboardingButtonLoginNextPreview() {
    KhanzaTheme {
        OnboardingButtonLabel(2)
    }
}
