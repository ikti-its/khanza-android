package dev.ikti.onboarding.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.ikti.core.domain.model.screen.Nav
import dev.ikti.core.domain.model.screen.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val token by viewModel.userToken.collectAsState("")
    val isNew by viewModel.isNewUser.collectAsState(false)

    LaunchedEffect(Unit) {
        viewModel.observeIsNewUser()
    }

    LaunchedEffect(token) {
        delay(1000L)
        if (token != "") {
            navController.popBackStack()
            navController.navigate(
                Nav.Home.route.replace(
                    "{token}",
                    token
                )
            )
        } else {
            navController.navigate(
                Screen.Onboarding.route.replace(
                    "{type}",
                    if (isNew) "new" else "old"
                )
            )
        }
    }
    SplashContent()
}
