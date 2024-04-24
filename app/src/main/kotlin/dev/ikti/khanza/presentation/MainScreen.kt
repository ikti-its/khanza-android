package dev.ikti.khanza.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    token: String = "",
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    var currentToken by remember { mutableStateOf("") }
    viewModel.apply {
        userToken.observe(LocalLifecycleOwner.current) { token ->
            currentToken = token
        }
    }

    MainContent(modifier = modifier, token = if (token == "") currentToken else token, navController = navController)
}
