package dev.ikti.auth.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.auth.presentation.util.AuthConstant.FIELD_TYPE_NIP
import dev.ikti.auth.presentation.util.AuthConstant.FIELD_TYPE_PASSWORD
import dev.ikti.core.presentation.theme.FontPlusJakartaSans
import dev.ikti.core.presentation.theme.Khanza50

@Composable
fun LoginFormLabel(
    field: String
) {
    when (field) {
        FIELD_TYPE_NIP -> {
            Text(
                text = "NIP",
                style = TextStyle(
                    color = Khanza50,
                    fontFamily = FontPlusJakartaSans
                )
            )
        }

        FIELD_TYPE_PASSWORD -> {
            Text(
                text = "Kata Sandi",
                style = TextStyle(
                    color = Khanza50,
                    fontFamily = FontPlusJakartaSans
                )
            )
        }
    }
}

@Preview
@Composable
fun LoginFormLabelPreview() {
    LoginFormLabel(FIELD_TYPE_NIP)
}
