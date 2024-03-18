package dev.ikti.auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {

}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun LoginScreenPreview() {
    KhanzaTheme {
        LoginScreen()
    }
}
