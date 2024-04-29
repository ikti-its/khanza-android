package dev.ikti.profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.util.UIState

@Composable
fun ProfileScreen(
    type: String = "view",
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val token by viewModel.token.collectAsState("")
    val stateProfile by viewModel.stateProfile.collectAsState(UIState.Loading)
    val userInfo by viewModel.userInfo

    LaunchedEffect(token) {
        if (token == "") {
            viewModel.getUserToken(Unit)
        } else {
            viewModel.getUserInfo(token)
        }
    }
    ProfileContent(
        type = type,
        stateProfile = stateProfile,
        userInfo = userInfo,
        navController = navController
    )
}
