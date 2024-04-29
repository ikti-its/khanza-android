package dev.ikti.core.domain.model.screen

import androidx.annotation.DrawableRes
import dev.ikti.core.R

sealed class Nav(
    val route: String,
    val label: String,
    @DrawableRes val unfocusedIcon: Int?,
    @DrawableRes val focusedIcon: Int?,
) {
    data object Home : Nav(
        "home/{token}",
        "Beranda",
        R.drawable.ic_outlined_home,
        R.drawable.ic_filled_home
    )

    data object Dummy : Nav(
        "dummy",
        "Fitur",
        R.drawable.ic_dev,
        R.drawable.ic_dev
    )

    data object Presensi : Nav(
        "presensi",
        "Presensi",
        R.drawable.ic_blank,
        R.drawable.ic_blank
    )

    data object Profile : Nav(
        "kepegawaian/akun/{type}",
        "Profil",
        R.drawable.ic_outlined_person,
        R.drawable.ic_filled_person
    )
}
