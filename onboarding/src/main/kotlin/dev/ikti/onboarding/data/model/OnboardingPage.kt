package dev.ikti.onboarding.data.model

import androidx.annotation.DrawableRes
import dev.ikti.onboarding.R

sealed class OnboardingPage(
    val title: String,
    val description: String,
) {
    object First : OnboardingPage(
        "Title 1",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )

    object Second : OnboardingPage(
        "Title 2",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )

    object Third : OnboardingPage(
        "Title 3",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )
}
