package dev.ikti.auth.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.auth.presentation.util.AuthConstant
import dev.ikti.core.presentation.theme.FontPlusJakartaSans
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun LoginFormSupportingText(
    field: String
) {
    Text( // TODO: i18n
        text = when (field) {
            AuthConstant.FIELD_TYPE_NIP -> "Username tidak boleh melebihi ${AuthConstant.MAXIMUM_NIP_LENGTH} karakter"
            AuthConstant.FIELD_TYPE_PASSWORD -> "Password tidak boleh melebihi ${AuthConstant.MAXIMUM_PASSWORD_LENGTH} karakter"
            else -> ""
        },
        style = TextStyle(
            color = Khanza50,
            fontFamily = FontPlusJakartaSans
        ),
        textAlign = TextAlign.End
    )
}

@Preview
@Composable
fun LoginFormSupportingTextPreview() {
    KhanzaTheme {
        LoginFormSupportingText(AuthConstant.FIELD_TYPE_NIP)
    }
}
