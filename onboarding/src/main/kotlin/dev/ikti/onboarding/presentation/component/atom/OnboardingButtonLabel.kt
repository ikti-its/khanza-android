package dev.ikti.onboarding.presentation.component.atom

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun OnboardingButtonLabel(
    selectedIndex: Int
) {
    if (selectedIndex != 2) {
        Text(
            text = "Selanjutnya",
            style = MaterialTheme.typography.labelLarge
        )
    } else {
        Text(
            text = "Masuk",
            style = MaterialTheme.typography.labelLarge
        )
    }
}