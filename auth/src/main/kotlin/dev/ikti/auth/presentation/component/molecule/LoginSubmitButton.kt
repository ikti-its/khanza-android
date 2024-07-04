package dev.ikti.auth.presentation.component.molecule

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
import dev.ikti.auth.presentation.component.atom.LoginSubmitLabel
import dev.ikti.core.presentation.theme.OMNIATheme

@Composable
fun LoginSubmitButton(
    modifier: Modifier = Modifier,
    onSubmit: () -> Unit
) {
    Button(
        onClick = { onSubmit() },
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonColors(
            containerColor = Color(0xFF0A2D27),
            contentColor = Color(0xFFACF2E7),
            disabledContainerColor = Color(0xFF8A8A8E),
            disabledContentColor = Color(0xFFFFFFFF)
        )
    ) {
        LoginSubmitLabel()
    }
}

@Preview
@Composable
fun LoginSubmitButtonPreview() {
    OMNIATheme {
        LoginSubmitButton {}
    }
}
