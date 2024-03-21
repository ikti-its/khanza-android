package dev.ikti.auth.presentation.component.molecule

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.auth.presentation.component.atom.LoginBackButton
import dev.ikti.auth.presentation.component.atom.LoginTitle
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.KhanzaDark
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun LoginAppBar(
    navigateBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            LoginTitle()
        },
        navigationIcon = {
            LoginBackButton(navigateBack = navigateBack)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = KhanzaDark,
            titleContentColor = Khanza50,
        )
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun LoginAppBarPreview() {
    KhanzaTheme {
        Scaffold(
            topBar = {
                LoginAppBar(navigateBack = {})
            },
        ) {
            it
        }
    }
}
