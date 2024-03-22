package dev.ikti.onboarding.presentation.component.atom

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.onboarding.presentation.model.OnboardingPage

@Composable
fun OnboardingTextDescription(
    onboardingPage: OnboardingPage
) {
    Text(
        text = stringResource(id = onboardingPage.description),
        color = Khanza50,
        style = MaterialTheme.typography.labelMedium
    )
}

@Preview
@Composable
fun OnboardingTextDescriptionPreview() {
    KhanzaTheme {
        OnboardingTextDescription(onboardingPage = OnboardingPage.First)
    }
}
