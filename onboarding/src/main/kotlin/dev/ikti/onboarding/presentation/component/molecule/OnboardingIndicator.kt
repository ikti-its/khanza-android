package dev.ikti.onboarding.presentation.component.molecule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.Khanza500
import dev.ikti.onboarding.presentation.component.atom.OnboardingIndicatorItem

@Composable
fun OnboardingIndicator(
    modifier: Modifier = Modifier,
    size: Int,
    selectedIndex: Int,
    selectedColor: Color = Khanza500,
    unselectedColor: Color = Khanza50
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(size) { page ->
            OnboardingIndicatorItem(
                modifier = modifier,
                width = 100,
                height = if (selectedIndex >= page) 4 else 2,
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
