package dev.ikti.profile.presentation.component.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun ProfileSpacer() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(Color(0xFFDCDCDC)))
}

@Preview
@Composable
fun ProfileSpacerPreview() {
    KhanzaTheme {
        ProfileSpacer()
    }
}
