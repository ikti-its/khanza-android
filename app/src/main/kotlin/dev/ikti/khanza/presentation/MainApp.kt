package dev.ikti.khanza.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dev.ikti.khanza.presentation.navigation.NavigationHost
import dev.ikti.khanza.presentation.navigation.model.BottomScreen
import dev.ikti.khanza.presentation.navigation.model.ModuleScreen

@Composable
fun MainApp() {
    NavigationHost(
        navController = rememberNavController(),
        startDestination = ModuleScreen.Onboarding.route
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun MainAppPreview() {
    MainApp()
}
