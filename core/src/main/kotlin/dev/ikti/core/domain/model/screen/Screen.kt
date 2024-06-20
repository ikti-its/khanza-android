package dev.ikti.core.domain.model.screen

sealed class Screen(
    val route: String
) {
    data object Splash : Screen("splash")
    data object Onboarding : Screen("onboarding/{type}")
    data object Login : Screen("login")
    data object Akun : Screen("kepegawaian/akun/{type}")
    data object Kehadiran : Screen("kepegawaian/kehadiran/{role}/{feature}")
    data object Pegawai : Screen("kepegawaian/pegawai/{role}/{feature}")
}
