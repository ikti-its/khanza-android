package dev.ikti.khanza.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.util.SetSystemUI

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    token: String = "",
    navController: NavHostController = rememberNavController()
) {
    val userHome by viewModel.userHome

    LaunchedEffect(token) {
        viewModel.getUserHome(token)
    }
    SetSystemUI(Color.Transparent, Color(0xFFF7F7F7))
    MainContent(
        modifier = modifier,
        token = token,
        userNama = userHome.nama,
        userStatus = userHome.status,
        userMasuk = userHome.jamMasuk,
        userPulang = userHome.jamPulang,
        navController = navController
    )
}
