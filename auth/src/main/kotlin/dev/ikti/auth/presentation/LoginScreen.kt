package dev.ikti.auth.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.ikti.auth.R
import dev.ikti.auth.presentation.util.ValidateInput
import dev.ikti.core.presentation.theme.FontPlusJakartaSans
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.Khanza500
import dev.ikti.core.presentation.theme.KhanzaDark
import dev.ikti.core.presentation.theme.KhanzaLight
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateToMain: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Log in",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontPlusJakartaSans
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = Khanza50
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = KhanzaDark,
                    titleContentColor = Khanza50,
                )
            )
        },
        containerColor = KhanzaDark,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .padding(it)
        ) {
            var username by rememberSaveable { mutableStateOf("") }
            val usernameLimit = 5
            var isUsernameError by rememberSaveable { mutableStateOf(false) }
            var password by rememberSaveable { mutableStateOf("") }
            var passwordHidden by rememberSaveable { mutableStateOf(true) }
            val passwordLimit = 20
            var isPasswordError by rememberSaveable { mutableStateOf(false) }

            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        isUsernameError = ValidateInput(username, usernameLimit)
                    },
                    modifier = modifier.fillMaxWidth(),
                    label = {
                        Text(
                            text = "Username",
                            style = TextStyle(
                                color = Khanza50,
                                fontFamily = FontPlusJakartaSans
                            )
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Username",
                            tint = Khanza50
                        )
                    },
                    supportingText = {
                        if (isUsernameError) {
                            Text(
                                text = "Username tidak boleh melebihi $usernameLimit karakter",
                                style = TextStyle(
                                    color = Khanza50,
                                    fontFamily = FontPlusJakartaSans
                                ),
                                textAlign = TextAlign.End
                            )
                        }
                    },
                    isError = isUsernameError,
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

            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        isPasswordError = ValidateInput(password, passwordLimit)
                    },
                    modifier = modifier.fillMaxWidth(),
                    label = {
                        Text(
                            text = "Password",
                            style = TextStyle(
                                color = Khanza50,
                                fontFamily = FontPlusJakartaSans
                            )
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password",
                            tint = Khanza50
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordHidden = !passwordHidden }) {
                            Icon(
                                painter = if (passwordHidden) {
                                    painterResource(id = R.drawable.ic_filled_eye)
                                } else {
                                    painterResource(id = R.drawable.ic_outlined_eye)
                                },
                                contentDescription = "Toggle password visibility",
                                tint = Khanza50
                            )
                        }
                    },
                    supportingText = {
                        if (isPasswordError) {
                            Text(
                                text = "Kata sandi tidak boleh melebihi $passwordLimit karakter",
                                style = TextStyle(
                                    color = Khanza50,
                                    fontFamily = FontPlusJakartaSans
                                ),
                                textAlign = TextAlign.End
                            )
                        }
                    },
                    isError = isPasswordError,
                    visualTransformation = if (passwordHidden) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

            Spacer(modifier = modifier.padding(vertical = 72.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { navigateToMain() },
                    modifier = modifier
                        .height(48.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonColors(
                        containerColor = Khanza500,
                        contentColor = KhanzaLight,
                        disabledContentColor = KhanzaDark,
                        disabledContainerColor = Khanza50
                    )
                ) {
                    Text(
                        text = "Log in",
                        modifier = modifier
                            .padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun LoginScreenPreview() {
    KhanzaTheme {
        LoginScreen(
            navigateBack = {},
            navigateToMain = {}
        )
    }
}
