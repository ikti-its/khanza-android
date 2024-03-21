package dev.ikti.onboarding.presentation.component.atom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.onboarding.R

@Composable
fun OnboardingBackground(
    modifier: Modifier,
    id: Int = R.drawable.onboard
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        alignment = Alignment.TopCenter
    )
}

@Preview
@Composable
fun OnboardingBackgroundPreview() {
    OnboardingBackground(modifier = Modifier, id = R.drawable.onboard)
}
