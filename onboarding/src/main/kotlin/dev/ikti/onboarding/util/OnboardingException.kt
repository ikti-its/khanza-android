package dev.ikti.onboarding.util

import dev.ikti.onboarding.util.OnboardingConstant.ERR_FAILED_TO_SET_USER

sealed class OnboardingException(message: String) : Exception(message) {
    object FailedToSetUserException : OnboardingException(ERR_FAILED_TO_SET_USER)
}
