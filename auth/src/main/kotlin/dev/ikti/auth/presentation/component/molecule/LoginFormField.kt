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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ikti.auth.presentation.component.atom.LoginFormLabel
import dev.ikti.auth.presentation.component.atom.LoginFormLeadingIcon
import dev.ikti.auth.presentation.component.atom.LoginFormTrailingIcon
import dev.ikti.auth.util.AuthConstant.FIELD_TYPE_EMAIL
import dev.ikti.auth.util.AuthConstant.FIELD_TYPE_PASSWORD
import dev.ikti.auth.util.AuthConstant.MAXIMUM_PASSWORD_LENGTH
import dev.ikti.auth.util.validateEmail
import dev.ikti.auth.util.validateInput
import dev.ikti.core.presentation.theme.FontGilroy

@Composable
fun LoginFormField(
    modifier: Modifier = Modifier,
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
                FIELD_TYPE_EMAIL -> {
                    isError = validateEmail(input)
                }

                FIELD_TYPE_PASSWORD -> {
                    isError = validateInput(input, MAXIMUM_PASSWORD_LENGTH)
                }
            }

            onValueChange(input, isError)
        },
        modifier = modifier.fillMaxWidth(),
        textStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            fontFamily = FontGilroy
        ),
        label = { LoginFormLabel(field) },
        leadingIcon = { LoginFormLeadingIcon(field) },
        trailingIcon = {
            if (field == FIELD_TYPE_PASSWORD) {
                LoginFormTrailingIcon(field) {
                    isPasswordHidden = it
                }
            }
        },
//        supportingText = {
//            if (isError) {
//                LoginFormSupportingText(field)
//            }
//        },
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
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Color(0xFF0A2D27),
            focusedTextColor = Color(0xFF272727),
            focusedBorderColor = Color(0xFFC4C4C4),
            unfocusedTextColor = Color(0xFF272727),
            unfocusedBorderColor = Color(0xFFC4C4C4),
            errorTextColor = Color(0xFFFF1300),
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun LoginFormFieldEmailPreview() {
    LoginFormField(
        field = FIELD_TYPE_EMAIL
    ) { _, _ -> }
}

@Preview(showBackground = true)
@Composable
fun LoginFormFieldPasswordPreview() {
    LoginFormField(
        field = FIELD_TYPE_PASSWORD
    ) { _, _ -> }
}

