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
import dev.ikti.onboarding.presentation.model.OnboardingPage

@Composable
fun OnboardingTextTitle(onboardingPage: OnboardingPage) {
    Text(
        text = stringResource(id = onboardingPage.title),
        color = Color(0xFF272727),
        style = TextStyle(
            color = Color(0xFF272727),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 42.sp,
            fontFamily = FontGilroy
        )
    )
}

@Preview
@Composable
fun OnboardingTextTitleFirstPreview() {
    OnboardingTextTitle(onboardingPage = OnboardingPage.First)
}

@Preview
@Composable
fun OnboardingTextTitleSecondPreview() {
    OnboardingTextTitle(onboardingPage = OnboardingPage.Second)
}

@Preview
@Composable
fun OnboardingTextTitleThirdPreview() {
    OnboardingTextTitle(onboardingPage = OnboardingPage.Third)
}
