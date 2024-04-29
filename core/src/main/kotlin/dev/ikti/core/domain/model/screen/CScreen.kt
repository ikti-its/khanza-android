package dev.ikti.core.domain.model.screen

sealed class CScreen(
    val route: String
) {
    data object Akun : CScreen("kepegawaian/akun/{type}")
    data object Kehadiran : CScreen("kepegawaian/kehadiran")
    data object Pegawai : CScreen("kepegawaian/pegawai")
}
