package dev.ikti.khanza.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    token: String = "",
    navController: NavHostController = rememberNavController()
) {
    MainContent(modifier = modifier, token = token, navController = navController)
}
