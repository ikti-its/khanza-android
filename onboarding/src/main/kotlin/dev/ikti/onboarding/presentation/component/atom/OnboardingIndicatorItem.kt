package dev.ikti.onboarding.presentation.component.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingIndicatorItem(
    width: Int = 100,
    height: Int = 4,
    color: Color = Color(0xFF0A2D27)
) {
    Box(
        modifier = Modifier
            .width(width.dp)
            .height(height.dp)
            .background(color, RoundedCornerShape(4.dp))
    )
}

@Preview()
@Composable
fun OnboardingIndicatorItemPreview() {
    OnboardingIndicatorItem()
}
