package dev.ikti.khanza.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.ikti.auth.presentation.LoginScreen
import dev.ikti.core.domain.model.screen.Nav
import dev.ikti.core.domain.model.screen.Screen
import dev.ikti.core.util.SetSystemUI
import dev.ikti.home.presentation.HomeScreen
import dev.ikti.kehadiran.presentation.KehadiranScreen
import dev.ikti.onboarding.presentation.OnboardingScreen
import dev.ikti.onboarding.presentation.splash.SplashScreen
import dev.ikti.pegawai.presentation.PegawaiScreen
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
        composable(Screen.Splash.route) {
            SetSystemUI(fullScreen = true)
            SplashScreen(navController = navController)
        }

        // Onboarding
        composable(
            route = Screen.Onboarding.route,
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                }
            ),
            enterTransition = { fadeIn(spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)) },
            exitTransition = { fadeOut(spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)) }
        ) {
            val type = it.arguments?.getString("type") ?: "new"
            SetSystemUI(fullScreen = true)
            OnboardingScreen(
                type = type,
                navController = navController
            )
        }

        // Auth
        composable(
            route = Screen.Login.route,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = spring(Spring.DampingRatioNoBouncy, Spring.StiffnessLow),
                    initialOffsetX = { it }
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = spring(Spring.DampingRatioNoBouncy, Spring.StiffnessMedium)
                )
            }
        ) {
            SetSystemUI(fullScreen = true)
            LoginScreen(navController = navController)
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
            val token = it.arguments?.getString("token") ?: ""
            SetSystemUI(
                Color(0xFF0A2D27),
                Color(0xFFFFFFFF)
            )
            HomeScreen(token = token, navController = navController)
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
                    animationSpec = spring(Spring.DampingRatioNoBouncy, Spring.StiffnessLow)
                )
            },
        ) { it ->
            val type = it.arguments?.getString("type") ?: "View"
            SetSystemUI(
                Color(0xFF0A2D27),
                Color(0xFFFFFFFF)
            )
            ProfileScreen(type = type, navController = navController)
        }

        // Kehadiran
        composable(
            route = Screen.Kehadiran.route,
            arguments = listOf(
                navArgument("role") {
                    type = NavType.StringType
                },
                navArgument("feature") {
                    type = NavType.StringType
                }
            ),
            enterTransition = { EnterTransition.None },
            exitTransition = { fadeOut(spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)) }
        ) {
            val role = it.arguments?.getString("role") ?: "Pegawai"
            val feature = it.arguments?.getString("feature") ?: "View"
            SetSystemUI(
                Color(0xFF0A2D27),
                Color(0xFFF7F7F7)
            )
            KehadiranScreen(
                role = role,
                feature = feature,
                navController = navController
            )
        }

        // Pegawai
        composable(
            route = Screen.Pegawai.route,
            arguments = listOf(
                navArgument("role") {
                    type = NavType.StringType
                },
                navArgument("feature") {
                    type = NavType.StringType
                }
            ),
            enterTransition = { EnterTransition.None },
            exitTransition = { fadeOut(spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)) }
        ) {
            val role = it.arguments?.getString("role") ?: "Pegawai"
            val feature = it.arguments?.getString("feature") ?: "View"
            SetSystemUI(
                Color(0xFF0A2D27),
                Color(0xFFF7F7F7)
            )
            PegawaiScreen(
                role = role,
                feature = feature,
                navController = navController
            )
        }
    }
}
