package dev.ikti.khanza.presentation.component.organism

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.presentation.component.molecule.HomeDropdown
import dev.ikti.khanza.presentation.component.molecule.HomeFeatureGrid
import dev.ikti.khanza.presentation.component.molecule.HomeHeroCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    modifier: Modifier,
    innerPadding: PaddingValues = PaddingValues(20.dp),
    token: String,
    navController: NavHostController
) {
//    val pullRefreshState = rememberPullRefreshState(
//        refreshing = ,
//        onRefresh = {}
//    )
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 60.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
        ) {
            HomeHeroCard(modifier = modifier)
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Color(0xFFF7F7F7))
        ) {
            Column(
                modifier = modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HomeDropdown(modifier = modifier) {
//                    TODO: Open up bottom sheet on click
                }
                Spacer(modifier = modifier.height(20.dp))
                HomeFeatureGrid(
                    modifier = modifier,
                    token = token,
                    navController = navController
                )
            }
        }
    }
}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun HomeContentPreview() {
    KhanzaTheme {
        HomeContent(
            modifier = Modifier,
            token = "",
            navController = rememberNavController()
        )
    }
}
