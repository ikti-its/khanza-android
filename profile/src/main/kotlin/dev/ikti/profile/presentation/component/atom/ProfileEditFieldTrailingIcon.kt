package dev.ikti.profile.presentation.component.atom

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import dev.ikti.profile.R
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_PASSWORD

@Composable
fun ProfileEditFieldTrailingIcon(
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
                contentDescription = null,
                tint = Color(0xFFC4C4C4)
            )
        }
    }
}