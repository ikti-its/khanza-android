package dev.ikti.khanza.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.ikti.auth.presentation.LoginScreen
import dev.ikti.core.domain.model.screen.CScreen
import dev.ikti.core.domain.model.screen.ModuleScreen
import dev.ikti.core.domain.model.screen.Nav
import dev.ikti.core.util.SetSystemUI
import dev.ikti.home.presentation.HomeScreen
import dev.ikti.onboarding.presentation.OnboardingScreen
import dev.ikti.onboarding.presentation.splash.AppSplashScreen
import dev.ikti.profile.presentation.ProfileScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        // Splash
        composable(ModuleScreen.Splash.route) {
            SetSystemUI(fullScreen = true)
            AppSplashScreen(
                navigateToHome = { token ->
                    navController.popBackStack()
                    navController.navigate(
                        Nav.Home.route.replace("{token}", token)
                    )
                },
                navigateToOnboarding = {
                    navController.navigate(ModuleScreen.Onboarding.route)
                }
            )
        }

        // Onboarding
        composable(ModuleScreen.Onboarding.route) {
            SetSystemUI(fullScreen = true)
            OnboardingScreen(
                navigateToLogin = {
                    navController.navigate(ModuleScreen.Login.route)
                }
            )
        }

        // Auth
        composable(ModuleScreen.Login.route) {
            SetSystemUI(fullScreen = true)
            LoginScreen(
                navigateToMain = { token ->
                    navController.navigate(
                        Nav.Home.route.replace("{token}", token)
                    ) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        // Home
        composable(
            route = Nav.Home.route,
            arguments = listOf(
                navArgument("token") {
                    type = NavType.StringType
                }
            ),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            val userToken = it.arguments?.getString("token") ?: ""
            SetSystemUI(
                Color(0xFF0A2D27),
                Color(0xFFFFFFFF),
                lightStatusBar = false,
                fullScreen = false
            )
            HomeScreen(token = userToken, navController = navController)
        }

        composable(Nav.Presensi.route) {
            // TODO: PresensiScreen()
        }

        // Modul C
        // Profile
        composable(
            route = Nav.Profile.route,
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                }
            ),
            enterTransition = { EnterTransition.None },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = spring(Spring.DampingRatioNoBouncy, Spring.StiffnessMedium)
                )
            },
        ) {
            val type = it.arguments?.getString("type") ?: "view"
            SetSystemUI(
                Color(0xFF0A2D27),
                Color(0xFFFFFFFF),
                lightStatusBar = false,
                fullScreen = false
            )
            ProfileScreen(type = type, navController = navController)
        }

        // Kehadiran
        composable(
            route = CScreen.Kehadiran.route
        ) {}

        // Pegawai
        composable(
            route = CScreen.Pegawai.route
        ) {}
    }
}
