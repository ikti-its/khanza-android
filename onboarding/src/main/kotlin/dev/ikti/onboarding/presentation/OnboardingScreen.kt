package dev.ikti.onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    type: String,
    navigateToLogin: () -> Unit
) {
    @OptIn(ExperimentalFoundationApi::class)
    OnboardingContent(
        modifier = modifier,
        type = type,
        navigateToLogin = navigateToLogin
    )
}
