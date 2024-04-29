package dev.ikti.core.domain.model.screen

sealed class ModuleScreen(
    val route: String
) {
    data object Splash : ModuleScreen("splash")
    data object Onboarding : ModuleScreen("onboarding")
    data object Login : ModuleScreen("login")
}
