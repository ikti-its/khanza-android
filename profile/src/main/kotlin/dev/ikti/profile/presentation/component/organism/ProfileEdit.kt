package dev.ikti.profile.presentation.component.organism

import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import dev.ikti.core.R
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.profile.data.model.ProfileRequest
import dev.ikti.profile.presentation.component.atom.ProfileDetailMapLabel
import dev.ikti.profile.presentation.component.molecule.ProfileEditField
import dev.ikti.profile.presentation.component.molecule.ProfileEditSubmitButton
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_ALAMAT
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_EMAIL
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_PASSWORD
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_ROLE

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProfileEdit(
    modifier: Modifier = Modifier,
    email: String = "user@fathoor.dev",
    role: String = "Developer",
    alamat: String = "Kampus ITS Surabaya",
    alamatLat: Double = 7.2575,
    alamatLon: Double = 112.7521,
    onSave: (ProfileRequest) -> Unit = {}
) {
    val locationPermissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    var newEmail by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable { mutableStateOf("") }
    var newAlamat by rememberSaveable { mutableStateOf("") }
    var isEmailError by rememberSaveable { mutableStateOf(false) }
    var isPasswordError by rememberSaveable { mutableStateOf(false) }
    var newAlamatLocation by rememberSaveable { mutableStateOf(LatLng(alamatLat, alamatLon)) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(newAlamatLocation, 15f)
    }

    LaunchedEffect(Unit) {
        if (!locationPermissionState.allPermissionsGranted) {
            locationPermissionState.launchMultiplePermissionRequest()
        }
    }

    Column(modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ubah foto profil",
                modifier = modifier.clickable { },
                style = TextStyle(
                    color = Color(0xFF272727),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    fontFamily = FontGilroy
                )
            )
        }
        Spacer(modifier.size(20.dp))
        ProfileEditField(field = FIELD_TYPE_EMAIL, placeholder = email) { value, error ->
            newEmail = value
            isEmailError = error
        }
        Spacer(modifier.size(10.dp))
        ProfileEditField(field = FIELD_TYPE_PASSWORD) { value, error ->
            newPassword = value
            isPasswordError = error
        }
        Spacer(modifier.size(10.dp))
        ProfileEditField(field = FIELD_TYPE_ROLE, placeholder = role) { _, _ -> }
        Spacer(modifier.size(10.dp))
        ProfileEditField(field = FIELD_TYPE_ALAMAT, placeholder = alamat) { value, _ ->
            newAlamat = value
        }
        Spacer(modifier.size(20.dp))
        ProfileDetailMapLabel()
        Spacer(modifier.size(10.dp))
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(16.dp)),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
                    LocalContext.current, R.raw.maps
                ),
                mapType = MapType.NORMAL
            ),
            uiSettings = MapUiSettings(
                mapToolbarEnabled = false,
                myLocationButtonEnabled = false,
                zoomControlsEnabled = false,
                compassEnabled = false,
                tiltGesturesEnabled = false
            ),
            onMapClick = {
            }
        ) {
            Marker(
                state = MarkerState(
                    position = newAlamatLocation
                ),
                onClick = {
                    true
                },
            )
        }
        Spacer(modifier.size(30.dp))
        ProfileEditSubmitButton(
            user = ProfileRequest(
                "",
                "https://api.fathoor.dev/v1/file/img/default.png",
                newEmail,
                newPassword,
                newAlamat,
                newAlamatLocation.latitude,
                newAlamatLocation.longitude
            ),
            onSave = onSave
        )
    }
}
