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
    navController: NavHostController = rememberNavController(),
    intentToMap: (String) -> Unit
) {
    val token by viewModel.token.collectAsState("")
    val stateProfile by viewModel.stateProfile.collectAsState(UIState.Empty)
    val stateLogout by viewModel.stateLogout.collectAsState(UIState.Empty)
    val stateUpload by viewModel.stateUpload.collectAsState(UIState.Empty)
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
        token = token,
        stateProfile = stateProfile,
        stateLogout = stateLogout,
        stateUpload = stateUpload,
        userInfo = userInfo,
        navController = navController,
        onLogout = { userToken ->
            viewModel.userLogout(userToken)
        },
        onSave = { user ->
            viewModel.userUpdate(token, user)
        },
        onUpload = { uri ->
            viewModel.uploadImage(token, uri)
        },
        intentToMap = intentToMap
    )
}
