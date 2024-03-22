package dev.ikti.onboarding.presentation.component.atom

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.onboarding.presentation.model.OnboardingPage

@Composable
fun OnboardingTextTitle(
    onboardingPage: OnboardingPage
) {
    Text(
        text = stringResource(id = onboardingPage.title),
        color = Khanza50,
        style = MaterialTheme.typography.titleLarge
    )
}

@Preview
@Composable
fun OnboardingTextTitlePreview() {
    OnboardingTextTitle(onboardingPage = OnboardingPage.First)
}
