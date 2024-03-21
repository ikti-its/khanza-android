package dev.ikti.auth.presentation.component.atom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun LoginBackButton(
    navigateBack: () -> Unit
) {
    IconButton(
        onClick = navigateBack
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
            contentDescription = "Kembali", // TODO: i18n
            tint = Khanza50
        )
    }
}

@Preview
@Composable
fun LoginBackButtonPreview() {
    KhanzaTheme {
        LoginBackButton(navigateBack = {})
    }
}
