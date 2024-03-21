package dev.ikti.auth.presentation.component.atom

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.auth.R
import dev.ikti.auth.util.AuthConstant.FIELD_TYPE_PASSWORD
import dev.ikti.core.presentation.theme.Khanza50

@Composable
fun LoginFormTrailingIcon(
    field: String,
    onHiddenToggle: (Boolean) -> Unit
) {
    var isPasswordHidden by rememberSaveable { mutableStateOf(true) }

    if (field == FIELD_TYPE_PASSWORD) {
        IconButton(
            onClick = {
                isPasswordHidden = !isPasswordHidden
                onHiddenToggle(isPasswordHidden)
            }
        ) {
            Icon(
                painter = if (isPasswordHidden) {
                    painterResource(id = R.drawable.ic_filled_eye)
                } else {
                    painterResource(id = R.drawable.ic_outlined_eye)
                },
                contentDescription = "Toggle password visibility",
                tint = Khanza50
            )
        }
    }
}

@Preview
@Composable
fun LoginFormTrailingIconPreview() {
    LoginFormTrailingIcon(FIELD_TYPE_PASSWORD) {}
}
