package dev.ikti.auth.presentation.component.atom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.auth.presentation.util.AuthConstant.FIELD_TYPE_NIP
import dev.ikti.auth.presentation.util.AuthConstant.FIELD_TYPE_PASSWORD
import dev.ikti.core.presentation.theme.Khanza50

@Composable
fun LoginFormLeadingIcon(
    field: String
) {
    when (field) {
        FIELD_TYPE_NIP -> {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = FIELD_TYPE_NIP,
                tint = Khanza50
            )
        }

        FIELD_TYPE_PASSWORD -> {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = FIELD_TYPE_PASSWORD,
                tint = Khanza50
            )
        }
    }
}

@Preview
@Composable
fun LoginFormLeadingIconPreview() {
    LoginFormLeadingIcon(FIELD_TYPE_NIP)
}

