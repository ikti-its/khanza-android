package dev.ikti.auth.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import dev.ikti.core.domain.model.screen.Nav

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val stateLogin by viewModel.stateLogin.collectAsState()
    val token by viewModel.userToken

    LoginContent(
        stateLogin = stateLogin,
        onSubmit = { nip, password ->
            viewModel.login(nip, password)
        },
        navigateToMain = {
            navController.navigate(
                Nav.Home.route.replace("{token}", token)
            ) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    )
}
