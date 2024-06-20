package dev.ikti.profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfileScreen(
    type: String = "View",
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val token by viewModel.token.collectAsState()
    val stateProfile by viewModel.stateProfile.collectAsState()
    val stateLogout by viewModel.stateLogout.collectAsState()
    val stateEdit by viewModel.stateEdit.collectAsState()
    val stateUpload by viewModel.stateUpload.collectAsState()
    val stateLocation by viewModel.stateLocation.collectAsState()

    LaunchedEffect(token) {
        if (token == "") {
            viewModel.getUserToken(Unit)
        } else {
            viewModel.getUserInfo(token)
        }
    }

    when (type) {
        "View" -> {
            ViewContent(
                stateProfile = stateProfile,
                stateLogout = stateLogout,
                onLogout = {
                    viewModel.userLogout(token)
                },
                navController = navController
            )
        }

        "Detail" -> {
            DetailContent(stateProfile = stateProfile, navController = navController)
        }

        "Edit" -> {
            EditContent(
                stateProfile = stateProfile,
                stateEdit = stateEdit,
                stateUpload = stateUpload,
                stateLocation = stateLocation,
                onSave = { user ->
                    viewModel.userUpdate(token, user)
                },
                onUpload = { uri ->
                    viewModel.uploadImage(token, uri)
                },
                onMarkerSearch = { lat, lon ->
                    viewModel.getMarkerAddress(lat, lon)
                },
                navController = navController
            )
        }
    }
}
