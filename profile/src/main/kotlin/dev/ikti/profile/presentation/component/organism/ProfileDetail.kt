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
import com.google.android.gms.maps.model.LatLng
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.profile.presentation.component.atom.ProfileDetailMapLabel
import dev.ikti.profile.presentation.component.molecule.ProfileDetailField
import dev.ikti.profile.presentation.component.molecule.ProfileDetailMap

@Composable
fun ProfileDetail(
    modifier: Modifier = Modifier,
    email: String = "user@fathoor.dev",
    role: String = "Developer",
    alamat: String = "Kampus ITS Surabaya",
    alamatLat: Double = -7.2821,
    alamatLon: Double = 112.7949,
    intentToMap: (String) -> Unit = {}
) {
    Column(modifier.fillMaxSize()) {
        Spacer(modifier.size(20.dp))
        ProfileDetailField(field = "email", text = email)
        Spacer(modifier.size(10.dp))
        ProfileDetailField(field = "role", text = role)
        Spacer(modifier.size(10.dp))
        ProfileDetailField(field = "alamat", text = alamat)
        Spacer(modifier.size(20.dp))
        ProfileDetailMapLabel()
        Spacer(modifier.size(10.dp))
        ProfileDetailMap(
            alamat = alamat,
            location = LatLng(alamatLat, alamatLon),
            intentToMap = intentToMap
        )
        Spacer(modifier.size(80.dp))
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
