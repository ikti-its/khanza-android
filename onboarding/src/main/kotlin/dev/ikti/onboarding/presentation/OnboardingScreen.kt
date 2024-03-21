package dev.ikti.onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.ikti.core.util.State

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit
) {
    val stateOnboarding by viewModel.stateOnboarding.collectAsState(initial = State.Empty)

    OnboardingContent(
        modifier = modifier,
        stateOnboarding = stateOnboarding,
        navigateToLogin = navigateToLogin,
        onSetNewUser = { state ->
            viewModel.setNewUser(state)
        }
    )
}
