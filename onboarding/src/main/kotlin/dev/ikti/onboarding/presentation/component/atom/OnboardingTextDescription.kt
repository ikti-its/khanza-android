package dev.ikti.onboarding.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.presentation.theme.OMNIATheme
import dev.ikti.onboarding.presentation.model.OnboardingPage

@Composable
fun OnboardingTextDescription(onboardingPage: OnboardingPage) {
    Text(
        text = stringResource(id = onboardingPage.description),
        style = TextStyle(
            color = Color(0xFF535353),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = FontGilroy
        )
    )
}

@Preview
@Composable
fun OnboardingTextDescriptionFirstPreview() {
    OMNIATheme {
        OnboardingTextDescription(onboardingPage = OnboardingPage.First)
    }
}

@Preview
@Composable
fun OnboardingTextDescriptionSecondPreview() {
    OMNIATheme {
        OnboardingTextDescription(onboardingPage = OnboardingPage.Second)
    }
}

@Preview
@Composable
fun OnboardingTextDescriptionThirdPreview() {
    OMNIATheme {
        OnboardingTextDescription(onboardingPage = OnboardingPage.Third)
    }
}
