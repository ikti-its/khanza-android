package dev.ikti.core.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.ikti.core.R

sealed class Screen(
    val route: String,
    @StringRes val label: Int?,
    @DrawableRes val unfocusedIcon: Int?,
    @DrawableRes val focusedIcon: Int?,
) {
    object Home : Screen(
        "home",
        R.string.navigation_home,
        R.drawable.ic_outlined_home,
        R.drawable.ic_filled_home
    )

    // Temporary
    object Search : Screen(
        "search",
        R.string.navigation_search,
        R.drawable.ic_search,
        R.drawable.ic_search
    )

    object Presensi : Screen(
        "presensi",
        R.string.navigation_presensi,
        R.drawable.ic_presensi,
        R.drawable.ic_presensi
    )

    // Temporary
    object History : Screen(
        "history",
        R.string.navigation_history,
        R.drawable.ic_history,
        R.drawable.ic_history
    )

    object Profile : Screen(
        "profile",
        R.string.navigation_profile,
        R.drawable.ic_outlined_person,
        R.drawable.ic_filled_person
    )
}
