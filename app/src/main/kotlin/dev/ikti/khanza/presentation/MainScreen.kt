package dev.ikti.khanza.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.presentation.component.BottomFAB
import dev.ikti.core.presentation.component.BottomNav
import dev.ikti.core.presentation.navigation.NavigationHost
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.KhanzaLight
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = modifier
                    .height(76.dp)
                    .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)),
                backgroundColor = KhanzaLight,
                cutoutShape = CircleShape,
                elevation = 2.dp,
                contentPadding = PaddingValues(horizontal = 10.dp)
            ) {
                BottomNav(
                    navController = navController
                )
            }
        },
        floatingActionButton = {
            Box {
                BottomFAB(
                    modifier = modifier,
                    navController = navController
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        backgroundColor = Khanza50 // Temporary
    ) {
        NavigationHost(
            navController = navController,
            innerPadding = it
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun MainAppPreview() {
    KhanzaTheme {
        MainScreen(
            Modifier,
            rememberNavController()
        )
    }
}
