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
import dev.ikti.khanza.presentation.navigation.model.CScreen
import dev.ikti.khanza.presentation.navigation.model.ModuleScreen
import dev.ikti.khanza.presentation.navigation.model.NavScreen
import dev.ikti.khanza.presentation.splash.AppSplashScreen
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
        composable(ModuleScreen.Splash.route) {
            AppSplashScreen(
                navigateToHome = { token ->
                    navController.popBackStack()
                    navController.navigate(NavScreen.Home.route.replace("{token}", token))
                },
                navigateToOnboarding = {
                    navController.navigate(ModuleScreen.Onboarding.route)
                }
            )
        }

        composable(
            route = NavScreen.Home.route,
            arguments = listOf(
                navArgument("token") {
                    type = NavType.StringType
                }
            )
        ) {
            val userToken = it.arguments?.getString("token") ?: ""
            MainScreen(token = userToken, navController = navController)
        }

        composable(NavScreen.Presensi.route) {
            // TODO: PresensiScreen()
        }

        composable(NavScreen.Profile.route) {
            // TODO: ProfileScreen()
        }

        // Temporary
        composable(NavScreen.Search.route) {}
        composable(NavScreen.History.route) {}

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
                    navController.navigate(NavScreen.Home.route.replace("{token}", token)) {
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
            val userToken = it.arguments?.getString("token") ?: ""
        }

        composable(
            route = CScreen.Kehadiran.route,
            arguments = listOf(
                navArgument("token") {
                    type = NavType.StringType
                }
            )
        ) {
            val userToken = it.arguments?.getString("token") ?: ""
        }

        composable(
            route = CScreen.Pegawai.route,
            arguments = listOf(
                navArgument("token") {
                    type = NavType.StringType
                }
            )
        ) {
            val userToken = it.arguments?.getString("token") ?: ""
        }
    }
}
