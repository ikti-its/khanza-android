package dev.ikti.khanza.presentation.navigation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.ikti.khanza.R

sealed class BottomScreen(
    val route: String,
    @StringRes val label: Int?,
    @DrawableRes val unfocusedIcon: Int?,
    @DrawableRes val focusedIcon: Int?,
) {
    data object Home : BottomScreen(
        "home/{token}",
        R.string.bottom_navigation_home,
        R.drawable.ic_outlined_home,
        R.drawable.ic_filled_home
    )

    // Temporary
    data object Search : BottomScreen(
        "dummy",
        R.string.bottom_navigation_dummy,
        R.drawable.ic_search,
        R.drawable.ic_search
    )

    data object Presensi : BottomScreen(
        "presensi/{token}",
        R.string.bottom_navigation_presensi,
        R.drawable.ic_presensi,
        R.drawable.ic_presensi
    )

    // Temporary
    data object History : BottomScreen(
        "dummy",
        R.string.bottom_navigation_dummy,
        R.drawable.ic_history,
        R.drawable.ic_history
    )

    data object Profile : BottomScreen(
        "profile/{token}",
        R.string.bottom_navigation_profile,
        R.drawable.ic_outlined_person,
        R.drawable.ic_filled_person
    )
}
