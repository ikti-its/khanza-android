package dev.ikti.onboarding.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.presentation.theme.OMNIATheme
import dev.ikti.onboarding.R

@Composable
fun OnboardingButtonLabel(selectedIndex: Int) {
    if (selectedIndex != 2) {
        Text(
            text = stringResource(id = R.string.next_button),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = FontGilroy
            )
        )
    } else {
        Text(
            text = stringResource(id = R.string.login_button),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = FontGilroy
            )
        )
    }
}

@Preview
@Composable
fun OnboardingButtonLabelNextPreview() {
    OMNIATheme {
        OnboardingButtonLabel(0)
    }
}

@Preview
@Composable
fun OnboardingButtonLoginNextPreview() {
    OMNIATheme {
        OnboardingButtonLabel(2)
    }
}
