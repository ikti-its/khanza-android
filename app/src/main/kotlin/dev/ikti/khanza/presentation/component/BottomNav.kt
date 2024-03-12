package dev.ikti.khanza.presentation.component

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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.ikti.khanza.presentation.navigation.model.BottomScreen
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.Khanza500
import dev.ikti.core.presentation.theme.KhanzaNavText
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun BottomNav(
    navController: NavHostController,
) {
    val items = listOf(
        BottomScreen.Home,
        BottomScreen.Search,
        BottomScreen.Presensi,
        BottomScreen.History,
        BottomScreen.Profile
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
                currentDestination?.hierarchy?.any { it.route == BottomScreen.Presensi.route } == true

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (screen.route != BottomScreen.Presensi.route) {
                        navController.navigate(screen.route) {
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
                                contentDescription = screen.label.toString()
                            )
                        }
                    }
                },
                label = {
                    Text(
                        text = screen.label?.let { stringResource(it) } ?: "",
                        style = MaterialTheme.typography.labelMedium,
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = Khanza500,
                    selectedTextColor = Khanza500,
                    selectedIndicatorColor = if (isPresensi) Color.Transparent else Khanza50,
                    unselectedIconColor = KhanzaNavText,
                    unselectedTextColor = KhanzaNavText,
                    disabledIconColor = KhanzaNavText,
                    disabledTextColor = KhanzaNavText
                )
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun BottomNavPreview() {
    KhanzaTheme {
        BottomNav(
            rememberNavController()
        )
    }
}