package dev.ikti.auth.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.auth.util.AuthConstant.FIELD_TYPE_EMAIL
import dev.ikti.auth.util.AuthConstant.FIELD_TYPE_PASSWORD
import dev.ikti.core.presentation.theme.FontPlusJakartaSans

@Composable
fun LoginFormLabel(
    field: String
) {
    when (field) {
        FIELD_TYPE_EMAIL -> {
            Text(
                text = "Email",
                style = TextStyle(
                    color = Color(0xFF272727),
                    fontFamily = FontPlusJakartaSans
                )
            )
        }

        FIELD_TYPE_PASSWORD -> {
            Text(
                text = "Password",
                style = TextStyle(
                    color = Color(0xFF272727),
                    fontFamily = FontPlusJakartaSans
                )
            )
        }
    }
}

@Preview
@Composable
fun LoginFormLabelPreview() {
    LoginFormLabel(FIELD_TYPE_EMAIL)
}
