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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.presentation.theme.KhanzaLight
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.presentation.component.BottomFAB
import dev.ikti.khanza.presentation.component.BottomNav
import dev.ikti.khanza.presentation.component.HomeContent
import dev.ikti.khanza.presentation.component.HomeHero

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
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                backgroundColor = KhanzaLight,
                cutoutShape = CircleShape,
                elevation = 0.dp,
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
        backgroundColor = Color.Transparent
    ) {
        HomeHero(
            modifier = modifier,
            innerPadding = it
        )
        HomeContent(
            modifier = modifier,
            navController = navController
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun MainScreenPreview() {
    KhanzaTheme {
        MainScreen(
            Modifier,
            rememberNavController()
        )
    }
}
