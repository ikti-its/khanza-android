package dev.ikti.profile.presentation.component.organism

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.profile.presentation.component.atom.ProfileDetailField

@Composable
fun ProfileDetail(
    modifier: Modifier = Modifier,
    email: String = "user@fathoor.dev",
    role: String = "Developer",
    alamat: String = "Kampus ITS Surabaya"
) {
    Column(modifier.fillMaxSize()) {
        Spacer(modifier.size(20.dp))
        ProfileDetailField(field = "email", text = email)
        Spacer(modifier.size(10.dp))
        ProfileDetailField(field = "role", text = role)
        Spacer(modifier.size(10.dp))
        ProfileDetailField(field = "alamat", text = alamat)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDetailPreview() {
    KhanzaTheme {
        Column(modifier = Modifier.padding(20.dp)) {
            ProfileDetail()
        }
    }
}
