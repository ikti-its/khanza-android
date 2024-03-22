package dev.ikti.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit
) {
    OnboardingContent(
        modifier = modifier,
        navigateToLogin = navigateToLogin,
        onSetNewUser = { state ->
            viewModel.setNewUser(state)
        }
    )
}
