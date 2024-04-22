package dev.ikti.onboarding.presentation.component.molecule

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.onboarding.presentation.component.atom.OnboardingButtonLabel

@Composable
fun OnboardingButton(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    label: @Composable () -> Unit = { OnboardingButtonLabel(selectedIndex) },
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonColors(
            containerColor = Color(0xFF007AFF),
            contentColor = Color(0xFFFFFFFF),
            disabledContainerColor = Color(0xFF8A8A8E),
            disabledContentColor = Color(0xFFFFFFFF)
        )
    ) {
        label()
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingButtonPreview() {
    KhanzaTheme {
        OnboardingButton(
            selectedIndex = 1,
        ) {}
    }
}
