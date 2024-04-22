package dev.ikti.onboarding.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontPlusJakartaSans
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.onboarding.presentation.model.OnboardingPage

@Composable
fun OnboardingTextDescription(
    onboardingPage: OnboardingPage
) {
    Text(
        text = stringResource(id = onboardingPage.description),
        style = TextStyle(
            color = Color(0xFF272727),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp,
            fontFamily = FontPlusJakartaSans
        )
    )
}

@Preview
@Composable
fun OnboardingTextDescriptionPreview() {
    KhanzaTheme {
        OnboardingTextDescription(onboardingPage = OnboardingPage.First)
    }
}
