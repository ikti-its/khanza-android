package dev.ikti.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.util.UIState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    token: String = "",
    navController: NavHostController = rememberNavController()
) {
    val userToken by viewModel.userToken.collectAsState(token)
    val stateHome by viewModel.stateHome.collectAsState(UIState.Loading)
    val userHome by viewModel.userHome

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
        token = userToken,
        userNama = userHome.nama,
        userStatus = userHome.status,
        userMasuk = userHome.jamMasuk,
        userPulang = userHome.jamPulang,
        navController = navController,
    )
}
