package dev.ikti.onboarding.presentation.component.atom

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun OnboardingButtonLabel() {
    Text(
        text = "Masuk",
        style = MaterialTheme.typography.labelLarge
    )
}