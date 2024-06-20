package dev.ikti.onboarding.presentation.component.molecule

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.onboarding.presentation.component.atom.OnboardingButtonLabel

@Composable
fun OnboardingButton(
    selectedIndex: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF0A2D27),
            contentColor = Color(0xFFACF2E7)
        )
    ) {
        OnboardingButtonLabel(selectedIndex)
    }
}

@Preview()
@Composable
fun OnboardingButtonPreview() {
    KhanzaTheme {
        OnboardingButton(
            selectedIndex = 1,
        ) {}
    }
}
