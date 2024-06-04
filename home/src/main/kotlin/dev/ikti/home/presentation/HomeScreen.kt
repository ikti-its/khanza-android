package dev.ikti.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.domain.model.screen.ModuleScreen
import dev.ikti.core.util.UIState
import dev.ikti.home.util.HomeConstant

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    token: String = "",
    navController: NavHostController = rememberNavController()
) {
    val userToken by viewModel.userToken.collectAsState(token)
    val stateHome by viewModel.stateHome.collectAsState(UIState.Loading)
    val stateLogout by viewModel.stateLogout.collectAsState(UIState.Empty)
    val userHome by viewModel.userHome

    LaunchedEffect(userToken) {
        if (userToken == "") {
            viewModel.getUserToken(Unit)
        } else {
            viewModel.getUserHome(userToken)

            stateHome.let { state ->
                when (state) {
                    is UIState.Error -> {
                        when (state.error) {
                            HomeConstant.ERR_ACCOUNT_UNAUTHORIZED -> {
                                viewModel.userLogout(userToken)
                                navController.navigate(ModuleScreen.Onboarding.route) {
                                    popUpTo(0) {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
    }
    HomeContent(
        modifier = modifier,
        stateHome = stateHome,
        stateLogout = stateLogout,
        token = userToken,
        userNama = userHome.nama,
        userStatus = userHome.status,
        userMasuk = userHome.jamMasuk,
        userPulang = userHome.jamPulang,
        navController = navController,
    )
}
