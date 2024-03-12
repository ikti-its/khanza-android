package dev.ikti.core.presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.R
import dev.ikti.core.presentation.navigation.model.BottomScreen
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.Khanza500

@Composable
fun BottomFAB(
    modifier: Modifier,
    navController: NavHostController,
) {
    FloatingActionButton(
        onClick = {
            BottomScreen.Presensi.route.let {
                navController.navigate(it) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        },
        modifier = modifier
            .size(64.dp),
        shape = CircleShape,
        containerColor = Khanza500,
        contentColor = Khanza50,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 3.dp,
            pressedElevation = 5.dp
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_scan),
            contentDescription = BottomScreen.Presensi.route,
            modifier = Modifier
                .size(32.dp)
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun BottomFABPreview() {
    BottomFAB(
        Modifier,
        rememberNavController()
    )
}