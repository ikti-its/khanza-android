package dev.ikti.onboarding.presentation.component.atom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.Khanza500

@Composable
fun OnboardingProgress(
    modifier: Modifier,
) {
    LinearProgressIndicator(
        modifier = modifier.fillMaxWidth(),
        color = Khanza500,
        trackColor = Khanza50,
        strokeCap = StrokeCap.Round
    )
}

@Preview
@Composable
fun OnboardingProgressPreview() {
    OnboardingProgress(modifier = Modifier)
}
