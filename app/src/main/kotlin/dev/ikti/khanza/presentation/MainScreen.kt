package dev.ikti.khanza.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FabPosition
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.domain.model.screen.AkunScreen
import dev.ikti.core.domain.model.screen.Nav
import dev.ikti.khanza.presentation.component.molecule.MainBottomAppBar
import dev.ikti.khanza.presentation.component.molecule.MainBottomFAB

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    navScreens: List<Nav> = listOf(
        Nav.Home,
        Nav.Dummy,
        Nav.Presensi,
        Nav.Dummy,
        Nav.Profile
    ),
    currentDestination: NavDestination?,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            when (currentDestination?.route) {
                Nav.Home.route, Nav.Presensi.route, Nav.Profile.route -> {
                    MainBottomAppBar(
                        screens = navScreens,
                        navController = navController
                    )
                }

                else -> {}
            }
        },
        floatingActionButton = {
            when (currentDestination?.route) {
                Nav.Home.route, Nav.Presensi.route, Nav.Profile.route -> {
                    MainBottomFAB(navController = navController)
                }

                else -> {}
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        backgroundColor = Color.Transparent
    ) { content() }
}
