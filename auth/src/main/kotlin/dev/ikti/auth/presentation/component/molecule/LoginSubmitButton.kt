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
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun LoginSubmitButton(
    modifier: Modifier,
    label: @Composable () -> Unit = { LoginSubmitLabel() },
    onSubmit: () -> Unit
) {
    Button(
        onClick = { onSubmit() },
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
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

@Preview
@Composable
fun LoginSubmitButtonPreview() {
    KhanzaTheme {
        LoginSubmitButton(modifier = Modifier) {}
    }
}
