package dev.ikti.onboarding.presentation.component.molecule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.onboarding.presentation.component.atom.OnboardingIndicatorItem

@Composable
fun OnboardingIndicator(
    size: Int,
    selectedIndex: Int,
    selectedColor: Color = Color(0xFF0A2D27),
    unselectedColor: Color = Color(0xFFACF2E7)
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(size) { page ->
            OnboardingIndicatorItem(
                width = 100,
                height = if (selectedIndex >= page) 4 else 3,
                color = if (selectedIndex >= page) selectedColor else unselectedColor
            )
        }
    }
}

@Preview
@Composable
fun OnboardingIndicatorPreview() {
    OnboardingIndicator(size = 3, selectedIndex = 1)
}
