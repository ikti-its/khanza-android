package dev.ikti.onboarding.presentation.component.atom

import androidx.compose.foundation.background
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
import dev.ikti.core.presentation.theme.Khanza500

@Composable
fun OnboardingIndicatorItem(
    modifier: Modifier,
    width: Int = 100,
    height: Int = 4,
    color: Color = Khanza500
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .width(width.dp)
            .height(height.dp)
            .background(color)
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingIndicatorItemPreview() {
    OnboardingIndicatorItem(modifier = Modifier)
}
