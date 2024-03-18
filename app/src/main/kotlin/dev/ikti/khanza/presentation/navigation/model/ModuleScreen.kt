package dev.ikti.khanza.presentation.navigation.model

sealed class ModuleScreen(
    val route: String
) {
    object Onboarding : ModuleScreen("onboarding")
    object Login : ModuleScreen("login")
}
