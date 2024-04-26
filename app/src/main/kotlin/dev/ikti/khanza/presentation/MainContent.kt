package dev.ikti.khanza.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
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
import dev.ikti.core.util.UIState
import dev.ikti.khanza.presentation.component.molecule.HomeBottomFAB
import dev.ikti.khanza.presentation.component.molecule.HomeBottomNav
import dev.ikti.khanza.presentation.component.organism.HomeContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent(
    modifier: Modifier,
    stateHome: UIState<Unit> = UIState.Empty,
    token: String,
    userNama: String = "PENGGUNA",
    userStatus: Boolean = false,
    userMasuk: String = "08:00",
    userPulang: String = "16:00",
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.navigationBarsPadding(),
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
        backgroundColor = Color(0xFF0A2D27)
    ) {
        HomeContent(
            modifier = modifier,
            stateHome = stateHome,
            token = token,
            userNama = userNama,
            userStatus = userStatus,
            userMasuk = userMasuk,
            userPulang = userPulang,
            navController = navController
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun MainContentPreview() {
    KhanzaTheme {
        MainContent(
            modifier = Modifier,
            token = "",
            navController = rememberNavController()
        )
    }
}