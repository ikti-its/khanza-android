package dev.ikti.auth.presentation.component.molecule

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.auth.presentation.component.atom.LoginFormLabel
import dev.ikti.auth.presentation.component.atom.LoginFormLeadingIcon
import dev.ikti.auth.presentation.component.atom.LoginFormSupportingText
import dev.ikti.auth.presentation.component.atom.LoginFormTrailingIcon
import dev.ikti.auth.util.AuthConstant.FIELD_TYPE_NIP
import dev.ikti.auth.util.AuthConstant.FIELD_TYPE_PASSWORD
import dev.ikti.auth.util.AuthConstant.MAXIMUM_NIP_LENGTH
import dev.ikti.auth.util.AuthConstant.MAXIMUM_PASSWORD_LENGTH
import dev.ikti.auth.util.validateInput
import dev.ikti.core.presentation.theme.Khanza50

@Composable
fun LoginFormField(
    modifier: Modifier,
    field: String,
    onValueChange: (String, Boolean) -> Unit
) {
    var value by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    var isPasswordHidden by rememberSaveable { mutableStateOf(true) }

    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            value = input

            when (field) {
                FIELD_TYPE_NIP -> {
                    isError = validateInput(input, MAXIMUM_NIP_LENGTH)
                }

                FIELD_TYPE_PASSWORD -> {
                    isError = validateInput(input, MAXIMUM_PASSWORD_LENGTH)
                }
            }

            onValueChange(input, isError)
        },
        modifier = modifier.fillMaxWidth(),
        label = { LoginFormLabel(field) },
        leadingIcon = { LoginFormLeadingIcon(field) },
        trailingIcon = {
            if (field == FIELD_TYPE_PASSWORD) {
                LoginFormTrailingIcon(field) {
                    isPasswordHidden = it
                }
            }
        },
        supportingText = {
            if (isError) {
                LoginFormSupportingText(field)
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
            FIELD_TYPE_PASSWORD -> KeyboardOptions(keyboardType = KeyboardType.Password)
            else -> KeyboardOptions.Default
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Khanza50,
            focusedBorderColor = Khanza50,
            unfocusedTextColor = Khanza50,
            unfocusedBorderColor = Khanza50,
            errorTextColor = Color(0xffd32f2f),
        ),
    )
}

@Preview
@Composable
fun LoginFormFieldPreview() {
    LoginFormField(
        modifier = Modifier,
        field = FIELD_TYPE_NIP
    ) { _, _ -> }
}

