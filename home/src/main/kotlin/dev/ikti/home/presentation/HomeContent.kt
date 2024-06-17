package dev.ikti.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.util.UIState
import dev.ikti.home.data.model.HomeResponse
import dev.ikti.home.presentation.component.molecule.HomeFeatureGrid
import dev.ikti.home.presentation.component.molecule.HomeHeroCard

@Composable
fun HomeContent(
    modifier: Modifier,
    stateHome: UIState<HomeResponse>,
    onLogout: () -> Unit,
    navController: NavHostController
) {
    MainScaffold(
        modifier = modifier.navigationBarsPadding(),
        background = Color(0xFF0A2D27)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                HomeHeroCard(
                    modifier = modifier,
                    stateHome = stateHome
                )
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(Color(0xFFF7F7F7))
            ) {
                Column(
                    modifier = modifier.padding(PaddingValues(24.dp)),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = modifier.height(8.dp))
                    HomeFeatureGrid(
                        modifier = modifier,
                        stateHome = stateHome,
                        navController = navController
                    )
                }
            }
        }
    }

    if (stateHome is UIState.Error) {
        onLogout()
    }
}
