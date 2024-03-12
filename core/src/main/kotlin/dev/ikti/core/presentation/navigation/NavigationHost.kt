package dev.ikti.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.ikti.core.presentation.navigation.model.BottomScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomScreen.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(BottomScreen.Home.route) {
            // TODO: HomeScreen()
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
