package dev.ikti.khanza.presentation.component.organism

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.presentation.component.molecule.HomeHeroBackground
import dev.ikti.khanza.presentation.component.molecule.HomeHeroCard
import dev.ikti.khanza.presentation.navigation.model.BottomScreen

@Composable
fun HomeHero(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentAlignment = Alignment.Center
        ) {
            HomeHeroBackground(modifier = modifier)
        }
    }
}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun HomeHeroPreview() {
    KhanzaTheme {
        HomeHero(Modifier)
    }
}
