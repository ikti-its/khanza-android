package dev.ikti.khanza.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.util.SetSystemUI

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    token: String = "",
    navController: NavHostController = rememberNavController()
) {
    SetSystemUI(Color.Transparent, Color(0xFFF7F7F7))
    MainContent(modifier = modifier, token = token, navController = navController)
}
