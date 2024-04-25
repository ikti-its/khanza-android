package dev.ikti.khanza.presentation.component.molecule

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.presentation.navigation.model.NavScreen

@Composable
fun HomeBottomNav(
    token: String,
    navController: NavHostController,
) {
    val items = listOf(
        NavScreen.Home,
        NavScreen.Search,
        NavScreen.Presensi,
        NavScreen.History,
        NavScreen.Profile
    )

    BottomNavigation(
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val isPresensi =
                currentDestination?.hierarchy?.any { it.route == NavScreen.Presensi.route } == true

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (screen.route != NavScreen.Presensi.route) {
                        navController.navigate(screen.route.replace("{token}", token)) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    if (isSelected) {
                        screen.focusedIcon?.let {
                            Icon(
                                painter = painterResource(id = it),
                                contentDescription = screen.label.toString()
                            )
                        }
                    } else {
                        screen.unfocusedIcon?.let {
                            Icon(
                                painter = painterResource(id = it),
                                contentDescription = screen.label.toString(),
                            )
                        }
                    }
                },
                enabled = screen.route != NavScreen.Presensi.route,
                label = {
                    Text(
                        text = screen.label?.let { stringResource(it) } ?: "",
                        color = if (isSelected) Color(0xFF0A2D27) else Color(0xFFCACACA),
                        style = MaterialTheme.typography.labelMedium,
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.Unspecified,
                    selectedTextColor = Color(0xFF0A2D27),
                    selectedIndicatorColor = if (isPresensi) Color.Transparent else Color.Unspecified,
                    unselectedIconColor = Color(0xFFCACACA),
                    unselectedTextColor = Color(0xFFCACACA),
                    disabledIconColor = Color(0xFFCACACA),
                    disabledTextColor = Color(0xFFCACACA)
                )
            )
        }
    }
}

@Preview
@Composable
fun HomeBottomNavPreview() {
    KhanzaTheme {
        HomeBottomNav(
            "",
            rememberNavController()
        )
    }
}