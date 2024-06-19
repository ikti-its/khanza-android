package dev.ikti.profile.presentation.component.atom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun ProfileTopButton(navigateBack: () -> Unit = {}) {
    IconButton(onClick = navigateBack) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = null,
            tint = Color(0xFFFFFFFF)
        )
    }
}

@Preview
@Composable
fun ProfileTopButtonPreview() {
    KhanzaTheme {
        ProfileTopButton()
    }
}