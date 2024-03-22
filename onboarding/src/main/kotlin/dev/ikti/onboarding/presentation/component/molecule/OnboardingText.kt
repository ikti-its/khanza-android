package dev.ikti.onboarding.presentation.component.molecule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.onboarding.presentation.model.OnboardingPage
import dev.ikti.onboarding.presentation.component.atom.OnboardingTextDescription
import dev.ikti.onboarding.presentation.component.atom.OnboardingTextTitle

@Composable
fun OnboardingText(
    modifier: Modifier,
    onboardingPage: OnboardingPage
) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        OnboardingTextTitle(onboardingPage = onboardingPage)

        Spacer(modifier = modifier.padding(16.dp))

        OnboardingTextDescription(onboardingPage = onboardingPage)
    }
}

@Preview(showBackground = false)
@Composable
fun OnboardingTextPreview() {
    KhanzaTheme {
        OnboardingText(
            modifier = Modifier,
            onboardingPage = OnboardingPage.First
        )
    }
}
