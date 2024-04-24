package dev.ikti.khanza.presentation.component.molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.KhanzaHomeBrush
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun HomeHeroBackground(
    modifier: Modifier
) {
    Box(modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(brush = KhanzaHomeBrush),
        ) {}
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color(0xFFF7F7F7).copy(alpha = 0.75f)),
        ) {}
    }
}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun HomeHeroBackgroundPreview() {
    KhanzaTheme {
        HomeHeroBackground(Modifier)
    }
}