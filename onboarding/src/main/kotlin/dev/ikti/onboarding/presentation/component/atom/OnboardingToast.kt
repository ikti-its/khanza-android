package dev.ikti.onboarding.presentation.component.atom

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable

@Composable
fun OnboardingToast(
    context: Context,
    type: String
) {
    Toast.makeText(
        context,
        when (type) { // TODO: i18n
            else -> "Terjadi kesalahan"
        },
        Toast.LENGTH_SHORT
    ).show()
}
