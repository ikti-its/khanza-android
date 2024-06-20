package dev.ikti.auth.presentation.component.molecule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.auth.presentation.component.atom.LoginWelcomeHeading
import dev.ikti.auth.presentation.component.atom.LoginWelcomeSubheading
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun LoginWelcomeText() {
    Column {
        Column {
            LoginWelcomeHeading()
        }
        Spacer(Modifier.height(15.dp))
        Column {
            LoginWelcomeSubheading()
        }
    }
}

@Preview
@Composable
fun LoginWelcomeTextPreview() {
    KhanzaTheme {
        LoginWelcomeText()
    }
}
