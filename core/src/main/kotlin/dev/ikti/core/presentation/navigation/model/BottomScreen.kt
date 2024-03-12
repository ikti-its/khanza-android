package dev.ikti.core.presentation.navigation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.ikti.core.R

sealed class BottomScreen(
    val route: String,
    @StringRes val label: Int?,
    @DrawableRes val unfocusedIcon: Int?,
    @DrawableRes val focusedIcon: Int?,
) {
    object Home : BottomScreen(
        "home",
        R.string.navigation_home,
        R.drawable.ic_outlined_home,
        R.drawable.ic_filled_home
    )

    // Temporary
    object Search : BottomScreen(
        "search",
        R.string.navigation_search,
        R.drawable.ic_search,
        R.drawable.ic_search
    )

    object Presensi : BottomScreen(
        "presensi",
        R.string.navigation_presensi,
        R.drawable.ic_presensi,
        R.drawable.ic_presensi
    )

    // Temporary
    object History : BottomScreen(
        "history",
        R.string.navigation_history,
        R.drawable.ic_history,
        R.drawable.ic_history
    )

    object Profile : BottomScreen(
        "profile",
        R.string.navigation_profile,
        R.drawable.ic_outlined_person,
        R.drawable.ic_filled_person
    )
}
