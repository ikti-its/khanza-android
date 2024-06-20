package dev.ikti.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import dev.ikti.core.domain.model.screen.Screen

@Composable
fun OnboardingScreen(
    type: String,
    navController: NavHostController
) {
    OnboardingContent(
        type = type,
        navigateToLogin = {
            navController.navigate(Screen.Login.route)
        }
    )
}
