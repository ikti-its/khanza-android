package dev.ikti.onboarding.presentation.model

import androidx.annotation.StringRes
import dev.ikti.onboarding.R

sealed class OnboardingPage(
    @StringRes val title: Int,
    @StringRes val description: Int,
) {
    data object First : OnboardingPage(
        R.string.onboard_first,
        R.string.onboard_first_description
    )

    data object Second : OnboardingPage(
        R.string.onboard_second,
        R.string.onboard_second_description
    )

    data object Third : OnboardingPage(
        R.string.onboard_third,
        R.string.onboard_third_description
    )
}
