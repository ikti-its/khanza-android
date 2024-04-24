package dev.ikti.khanza.presentation.navigation.model

sealed class ModuleScreen(
    val route: String
) {
    data object Onboarding : ModuleScreen("onboarding")
    data object Login : ModuleScreen("login")
}
