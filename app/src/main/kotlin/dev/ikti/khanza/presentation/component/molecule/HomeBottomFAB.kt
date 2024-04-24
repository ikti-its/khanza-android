package dev.ikti.khanza.presentation.component.molecule

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.R
import dev.ikti.khanza.presentation.navigation.model.BottomScreen

@Composable
fun HomeBottomFAB(
    modifier: Modifier,
    token: String,
    navController: NavHostController,
) {
    FloatingActionButton(
        onClick = {
            navController.navigate(BottomScreen.Presensi.route.replace("{token}", token)) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        modifier = modifier
            .size(64.dp),
        shape = CircleShape,
        containerColor = Color(0xFF007AFF),
        contentColor = Color(0xFFF7F7F7),
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 3.dp,
            pressedElevation = 5.dp
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_scan),
            contentDescription = BottomScreen.Presensi.route,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Preview
@Composable
fun HomeBottomFABPreview() {
    KhanzaTheme {
        HomeBottomFAB(
            Modifier,
            "",
            rememberNavController()
        )
    }
}