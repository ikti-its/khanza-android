package dev.ikti.onboarding.presentation.component.molecule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.onboarding.data.model.OnboardingPage

@Composable
fun OnboardingPager(
    modifier: Modifier,
    bottomPadding: Int = 186,
    onboardingPage: OnboardingPage
) {
    Box(modifier = modifier.padding(bottom = bottomPadding.dp)) {
        OnboardingText(
            modifier = modifier,
            onboardingPage = onboardingPage
        )
    }
}

@Preview
@Composable
fun OnboardingPagerPreview() {
    KhanzaTheme {
        OnboardingPager(
            modifier = Modifier,
            onboardingPage = OnboardingPage.First
        )
    }
}

