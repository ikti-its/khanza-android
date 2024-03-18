package dev.ikti.khanza.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.ikti.auth.presentation.login.LoginScreen
import dev.ikti.khanza.presentation.MainScreen
import dev.ikti.khanza.presentation.navigation.model.BottomScreen
import dev.ikti.khanza.presentation.navigation.model.ModuleScreen
import dev.ikti.onboarding.presentation.OnboardingScreen

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

        // Onboarding
        composable(ModuleScreen.Onboarding.route) {
            OnboardingScreen(
                navigateToLogin = {
                    navController.navigate(ModuleScreen.Login.route)
                }
            )
        }

        // Auth
        composable(ModuleScreen.Login.route) {
            LoginScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToMain = {
                    navController.navigate(BottomScreen.Home.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
