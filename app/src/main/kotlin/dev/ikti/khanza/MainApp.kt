package dev.ikti.khanza

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.ikti.khanza.navigation.NavigationHost
import dev.ikti.khanza.presentation.MainScreen

@Composable
fun MainApp(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    MainScreen(navController = navController, currentDestination = currentDestination) {
        NavigationHost(
            navController = navController,
            startDestination = startDestination
        )
    }
}
