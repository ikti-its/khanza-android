package dev.ikti.omnia.presentation.component.molecule

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.domain.model.screen.Nav

@Composable
fun MainBottomAppBar(
    modifier: Modifier = Modifier,
    screens: List<Nav> = listOf(),
    navController: NavHostController = rememberNavController()
) {
    BottomAppBar(
        modifier = modifier
            .height(80.dp)
            .shadow(50.dp),
        backgroundColor = Color(0xFFFFFFFF),
        cutoutShape = CircleShape,
        elevation = 0.dp,
    ) {
        MainBottomAppBarNavigation(
            screens = screens,
            navController = navController
        )
    }
}

@Composable
fun MainBottomAppBarNavigation(
    screens: List<Nav>,
    navController: NavHostController
) {
    BottomNavigation(
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        screens.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val isPresensi =
                currentDestination?.route == Nav.Presensi.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(
                        when (screen.route) {
                            Nav.Profile.route -> screen.route.replace("{type}", "View")
                            else -> screen.route
                        }
                    ) {
                        when (screen.route) {
                            Nav.Profile.route, Nav.Presensi.route -> {}
                            Nav.Home.route -> {
                                popUpTo(0) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    }
                },
                icon = {
                    if (isSelected) {
                        screen.focusedIcon?.let {
                            Icon(
                                painter = painterResource(id = it),
                                contentDescription = null
                            )
                        }
                    } else {
                        screen.unfocusedIcon?.let {
                            Icon(
                                painter = painterResource(id = it),
                                contentDescription = null,
                            )
                        }
                    }
                },
                enabled = when (screen.route) {
                    Nav.Presensi.route -> false
                    else -> true
                },
                label = {
                    Text(
                        text = screen.label,
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
