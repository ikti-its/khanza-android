package dev.ikti.auth.presentation.component.atom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.auth.util.AuthConstant.FIELD_TYPE_EMAIL
import dev.ikti.auth.util.AuthConstant.FIELD_TYPE_PASSWORD

@Composable
fun LoginFormLeadingIcon(
    field: String
) {
    when (field) {
        FIELD_TYPE_EMAIL -> {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = FIELD_TYPE_EMAIL,
                tint = Color(0xFFC4C4C4)
            )
        }

        FIELD_TYPE_PASSWORD -> {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = FIELD_TYPE_PASSWORD,
                tint = Color(0xFFC4C4C4)
            )
        }
    }
}

@Preview
@Composable
fun LoginFormLeadingIconPreview() {
    LoginFormLeadingIcon(FIELD_TYPE_EMAIL)
}

