package dev.ikti.profile.presentation.component.molecule

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.profile.presentation.component.atom.ProfileEditFieldTrailingIcon
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_ALAMAT
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_EMAIL
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_PASSWORD
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_ROLE
import dev.ikti.profile.util.ProfileConstant.MAXIMUM_PASSWORD_LENGTH
import dev.ikti.profile.util.ProfileConstant.MINIMUM_PASSWORD_LENGTH
import dev.ikti.profile.util.validateEmail
import dev.ikti.profile.util.validateInput

@Composable
fun ProfileEditField(
    modifier: Modifier = Modifier,
    field: String = "",
    placeholder: String = "",
    onAlamatClick: () -> Unit = {},
    onValueChange: (String, Boolean) -> Unit
) {
    var value by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    var isPasswordHidden by rememberSaveable { mutableStateOf(true) }

    TextField(
        value = if (value == "") placeholder else value,
        onValueChange = { input ->
            value = input

            when (field) {
                FIELD_TYPE_EMAIL -> {
                    isError = validateEmail(input)
                }

                FIELD_TYPE_PASSWORD -> {
                    isError = validateInput(input, MINIMUM_PASSWORD_LENGTH, MAXIMUM_PASSWORD_LENGTH)
                }

                else -> {}
            }

            onValueChange(input, isError)
        },
        modifier = if (field == FIELD_TYPE_ALAMAT) modifier.fillMaxWidth().clickable { onAlamatClick() } else modifier.fillMaxWidth(),
        enabled = when (field) {
            FIELD_TYPE_EMAIL, FIELD_TYPE_PASSWORD -> true
            else -> false
        },
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = FontGilroy
        ),
        label = {
            Text(
                text = when (field) {
                    FIELD_TYPE_EMAIL -> "Email"
                    FIELD_TYPE_PASSWORD -> "Kata sandi"
                    FIELD_TYPE_ROLE -> "Role"
                    FIELD_TYPE_ALAMAT -> "Alamat Lengkap"
                    else -> ""
                },
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    fontFamily = FontGilroy
                )
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(
                    color = Color(0xFF272727),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    fontFamily = FontGilroy
                )
            )
        },
        trailingIcon = {
            if (field == FIELD_TYPE_PASSWORD) {
                ProfileEditFieldTrailingIcon(field) {
                    isPasswordHidden = it
                }
            }
        },
        isError = isError,
        visualTransformation = when (field) {
            FIELD_TYPE_PASSWORD -> {
                if (isPasswordHidden) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                }
            }

            else -> VisualTransformation.None
        },
        keyboardOptions = when (field) {
            FIELD_TYPE_EMAIL -> KeyboardOptions(keyboardType = KeyboardType.Email)
            FIELD_TYPE_PASSWORD -> KeyboardOptions(keyboardType = KeyboardType.Password)
            else -> KeyboardOptions.Default
        },
        singleLine = when (field) {
            FIELD_TYPE_ALAMAT -> false
            else -> true
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF7F7F7),
            focusedTextColor = Color(0xFF272727),
            focusedLabelColor = Color(0xFF272727),
            unfocusedContainerColor = Color(0xFFF7F7F7),
            unfocusedTextColor = Color(0xFF272727),
            unfocusedLabelColor = Color(0xFF272727),
            disabledContainerColor = Color(0xFFF7F7F7),
            disabledIndicatorColor = if (field == FIELD_TYPE_ALAMAT) Color(0xFF272727) else Color(
                0xFFC4C4C4
            ),
            disabledLabelColor = if (field == FIELD_TYPE_ALAMAT) Color(0xFF272727) else Color(
                0xFFC4C4C4
            ),
            disabledTextColor = if (field == FIELD_TYPE_ALAMAT) Color(0xFF272727) else Color(
                0xFFC4C4C4
            )
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileEditFieldEmailPreview() {
    ProfileEditField(
        field = FIELD_TYPE_EMAIL,
        placeholder = "user@fathoor.dev"
    ) { _, _ -> }
}

@Preview(showBackground = true)
@Composable
fun ProfileEditFieldPasswordPreview() {
    ProfileEditField(
        field = FIELD_TYPE_PASSWORD,
        placeholder = ""
    ) { _, _ -> }
}
