package dev.ikti.onboarding.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.Khanza500
import dev.ikti.core.presentation.theme.KhanzaDark
import dev.ikti.core.presentation.theme.KhanzaLight
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.onboarding.R

@Composable
fun OnboardingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onClick,
            modifier = modifier
                .height(48.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonColors(
                containerColor = Khanza500,
                contentColor = KhanzaLight,
                disabledContentColor = KhanzaDark,
                disabledContainerColor = Khanza50
            )
        ) {
            Text(
                text = stringResource(R.string.login_button),
                modifier = modifier
                    .padding(horizontal = 16.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginButtonPreview() {
    KhanzaTheme {
        OnboardingButton {}
    }
}
