package dev.ikti.auth.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontPlusJakartaSans
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun LoginTitle() {
    Text(
        "Masuk", // TODO: i18n
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            fontFamily = FontPlusJakartaSans
        )
    )
}

@Preview(showBackground = true)
@Composable
fun LoginTitlePreview() {
    KhanzaTheme {
        LoginTitle()
    }
}
