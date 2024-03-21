package dev.ikti.auth.presentation.component.atom

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun LoginSubmitLabel(
) {
    Text(
        text = "Masuk",
        style = MaterialTheme.typography.labelLarge
    )
}

@Preview
@Composable
fun LoginSubmitLabelPreview() {
    KhanzaTheme {
        LoginSubmitLabel()
    }
}
