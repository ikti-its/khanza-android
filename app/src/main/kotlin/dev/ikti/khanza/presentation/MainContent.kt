package dev.ikti.khanza.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomAppBar
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FabPosition
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.presentation.component.molecule.HomeBottomFAB
import dev.ikti.khanza.presentation.component.molecule.HomeBottomNav
import dev.ikti.khanza.presentation.component.organism.HomeContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent(
    modifier: Modifier,
    token: String,
    navController: NavHostController
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = modifier
                    .height(76.dp)
                    .shadow(48.dp),
                backgroundColor = Color(0xFFF7F7F7),
                cutoutShape = CircleShape,
                elevation = 0.dp,
            ) {
                HomeBottomNav(
                    token = token,
                    navController = navController
                )
            }
        },
        floatingActionButton = {
            Box {
                HomeBottomFAB(
                    modifier = modifier,
                    token = token,
                    navController = navController
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        backgroundColor = Color(0xFFE9F6FF)
    ) {
        HomeContent(
            modifier = modifier,
            token = token,
            navController = navController
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun MainContentPreview() {
    KhanzaTheme {
        MainContent(
            Modifier,
            "",
            rememberNavController()
        )
    }
}