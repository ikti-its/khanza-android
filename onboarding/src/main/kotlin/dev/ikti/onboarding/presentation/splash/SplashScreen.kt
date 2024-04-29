package dev.ikti.onboarding.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@Composable
fun AppSplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToHome: (String) -> Unit,
    navigateToOnboarding: (Boolean) -> Unit
) {
    val token by viewModel.userToken.collectAsState("")
    val isNew by viewModel.isNewUser.collectAsState(false)
    val isLoading by viewModel.isLoading.collectAsState(true)

    viewModel.observeIsNewUser(Unit)

    LaunchedEffect(isLoading) {
        delay(500L)

        if (token != "") {
            navigateToHome(token)
        } else {
            navigateToOnboarding(isNew)
        }
    }
    AppSplashContent()
}
