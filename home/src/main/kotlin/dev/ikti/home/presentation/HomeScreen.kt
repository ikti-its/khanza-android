package dev.ikti.home.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.domain.model.screen.Screen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    token: String,
    navController: NavHostController = rememberNavController()
) {
    val activity = (LocalContext.current as? Activity)
    val userToken by viewModel.userToken.collectAsState(token)
    val restricted by viewModel.restricted.collectAsState()
    val stateHome by viewModel.stateHome.collectAsState()

    LaunchedEffect(userToken) {
        if (userToken == "") {
            viewModel.getUserToken(Unit)
        } else {
            viewModel.getUserHome(userToken)
        }
    }
    HomeContent(
        modifier = modifier,
        stateHome = stateHome,
        onLogout = {
            if (!restricted) {
                viewModel.userLogout(userToken)
                navController.navigate(Screen.Onboarding.route) {
                    popUpTo(0) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            } else {
                activity?.finish()
            }
        },
        navController = navController,
    )
}
