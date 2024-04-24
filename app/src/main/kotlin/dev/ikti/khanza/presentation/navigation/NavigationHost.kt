package dev.ikti.khanza.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.ikti.auth.presentation.LoginScreen
import dev.ikti.khanza.presentation.MainScreen
import dev.ikti.khanza.presentation.navigation.model.BottomScreen
import dev.ikti.khanza.presentation.navigation.model.CScreen
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
        composable(
            route = BottomScreen.Home.route,
            arguments = listOf(
                navArgument("token") {
                    type = NavType.StringType
                }
            )
        ) {
            val userToken = it.arguments?.getString("token") ?: ""
            MainScreen(token = userToken, navController = navController)
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
                navigateToMain = { token ->
                    navController.navigate(BottomScreen.Home.route.replace("{token}", token)) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        // Modul C
        composable(
            route = CScreen.Akun.route,
            arguments = listOf(
                navArgument("token") {
                    type = NavType.StringType
                }
            )
        ) {
            val token = it.arguments?.getString("token") ?: ""
        }

        composable(
            route = CScreen.Kehadiran.route,
            arguments = listOf(
                navArgument("token") {
                    type = NavType.StringType
                }
            )
        ) {
            val token = it.arguments?.getString("token") ?: ""
        }

        composable(
            route = CScreen.Pegawai.route,
            arguments = listOf(
                navArgument("token") {
                    type = NavType.StringType
                }
            )
        ) {
            val token = it.arguments?.getString("token") ?: ""
        }
    }
}
