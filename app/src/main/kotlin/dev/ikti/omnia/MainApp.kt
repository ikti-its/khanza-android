package dev.ikti.omnia

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.ikti.omnia.navigation.NavigationHost
import dev.ikti.omnia.presentation.MainScreen

@Composable
fun MainApp(startDestination: String) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    MainScreen(navController = navController, currentDestination = currentDestination) {
        NavigationHost(
            navController = navController,
            startDestination = startDestination
        )
    }
}