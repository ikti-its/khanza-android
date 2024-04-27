package dev.ikti.khanza.navigation.model

sealed class CScreen(
    val route: String
) {
    data object Akun : CScreen("kepegawaian/akun/{token}")
    data object Kehadiran : CScreen("kepegawaian/kehadiran/{token}")
    data object Pegawai : CScreen("kepegawaian/pegawai/{token}")
}
