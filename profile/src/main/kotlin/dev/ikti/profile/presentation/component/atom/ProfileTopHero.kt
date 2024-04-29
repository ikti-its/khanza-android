package dev.ikti.profile.presentation.component.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun ProfileTopHero(modifier: Modifier = Modifier) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(Color(0xFF0A2D27))
        ) {}
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(Color(0xFFACF2E7))
        ) {}
    }
}

@Preview
@Composable
fun ProfileTopHeroPreview() {
    KhanzaTheme {
        ProfileTopHero()
    }
}
