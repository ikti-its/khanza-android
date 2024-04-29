package dev.ikti.core.domain.model.screen

sealed class AkunScreen(
    val route: String,
    val title: String,
) {
    data object Halaman : AkunScreen(
        route = "kepegawaian/akun/view",
        title = "Halaman Profil"
    )

    data object Detail : AkunScreen(
        route = "kepegawaian/akun/detail",
        title = "Detail Profil"
    )

    data object Edit : AkunScreen(
        route = "kepegawaian/akun/edit",
        title = "Ubah Profil"
    )
}