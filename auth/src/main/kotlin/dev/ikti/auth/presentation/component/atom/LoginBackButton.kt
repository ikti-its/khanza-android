package dev.ikti.auth.presentation.component.atom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun LoginBackButton(navigateBack: () -> Unit) {
    IconButton(onClick = navigateBack) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
            contentDescription = null,
            tint = Color(0xFF272727)
        )
    }
}

@Preview
@Composable
fun LoginBackButtonPreview() {
    KhanzaTheme {
        LoginBackButton {}
    }
}
