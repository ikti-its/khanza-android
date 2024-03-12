package dev.ikti.khanza.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.ikti.khanza.presentation.MainScreen
import dev.ikti.khanza.presentation.navigation.model.BottomScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(BottomScreen.Home.route) {
            MainScreen(navController = navController)
        }

        composable(BottomScreen.Presensi.route) {
            // TODO: PresensiScreen()
        }

        composable(BottomScreen.Profile.route) {
            // TODO: ProfileScreen()
        }

        // Temporary
        composable(BottomScreen.Search.route) {}
        composable(BottomScreen.History.route) {}
    }
}
