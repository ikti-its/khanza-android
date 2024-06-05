package dev.ikti.profile.presentation.component.organism

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.ikti.profile.presentation.component.atom.ProfileDetailMapLabel
import dev.ikti.profile.presentation.component.molecule.ProfileDetailField
import dev.ikti.profile.presentation.component.molecule.ProfileDetailMap

@Composable
fun ProfileDetail(
    modifier: Modifier = Modifier,
    email: String,
    role: String,
    alamat: String,
    alamatLat: Double,
    alamatLon: Double,
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
            latitude = alamatLat,
            longitude = alamatLon,
            intentToMap = intentToMap
        )
        Spacer(modifier.size(80.dp))
    }
}
