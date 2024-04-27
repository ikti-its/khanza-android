package dev.ikti.khanza.navigation.model

sealed class ModuleScreen(
    val route: String
) {
    data object Splash : ModuleScreen("splash")
    data object Onboarding : ModuleScreen("onboarding")
    data object Login : ModuleScreen("login")
}
