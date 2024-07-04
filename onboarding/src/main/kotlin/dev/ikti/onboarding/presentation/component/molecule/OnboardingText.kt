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
import dev.ikti.core.presentation.theme.OMNIATheme
import dev.ikti.onboarding.presentation.component.atom.OnboardingTextDescription
import dev.ikti.onboarding.presentation.component.atom.OnboardingTextTitle
import dev.ikti.onboarding.presentation.model.OnboardingPage

@Composable
fun OnboardingText(
    onboardingPage: OnboardingPage
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        OnboardingTextTitle(onboardingPage = onboardingPage)
        Spacer(modifier = Modifier.padding(12.dp))
        OnboardingTextDescription(onboardingPage = onboardingPage)
    }
}

@Preview(showBackground = false)
@Composable
fun OnboardingTextFirstPreview() {
    OMNIATheme {
        OnboardingText(
            onboardingPage = OnboardingPage.First
        )
    }
}

@Preview(showBackground = false)
@Composable
fun OnboardingTextSecondPreview() {
    OMNIATheme {
        OnboardingText(
            onboardingPage = OnboardingPage.Second
        )
    }
}

@Preview(showBackground = false)
@Composable
fun OnboardingTextThirdPreview() {
    OMNIATheme {
        OnboardingText(
            onboardingPage = OnboardingPage.Third
        )
    }
}
