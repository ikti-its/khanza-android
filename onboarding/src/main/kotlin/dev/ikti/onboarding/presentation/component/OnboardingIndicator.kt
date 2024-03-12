package dev.ikti.onboarding.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.Khanza500

@Composable
fun OnboardingIndicator(
    modifier: Modifier = Modifier,
    size: Int,
    selectedIndex: Int,
    selectedColor: Color = Khanza500,
    unselectedColor: Color = Khanza50
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement  = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(size) { page ->
            Box(
                modifier = modifier
                    .width(100.dp)
                    .height(
                        if (page <= selectedIndex) 4.dp else 2.dp
                    )
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        if (page <= selectedIndex) selectedColor else unselectedColor
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PageIndicatorPreview() {
    OnboardingIndicator(size = 3, selectedIndex = 1)
}
