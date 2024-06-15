package dev.ikti.profile.presentation.component.organism

import android.Manifest
import android.location.Address
import android.net.Uri
import android.view.MotionEvent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
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
import dev.ikti.core.presentation.component.Shimmer
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.util.UIState
import dev.ikti.core.util.file.FileConstant.ERR_UNSUPPORTED
import dev.ikti.profile.data.model.ProfileRequest
import dev.ikti.profile.presentation.component.atom.ProfileDetailMapLabel
import dev.ikti.profile.presentation.component.molecule.ProfileEditField
import dev.ikti.profile.presentation.component.molecule.ProfileEditMap
import dev.ikti.profile.presentation.component.molecule.ProfileEditSubmitButton
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_ALAMAT
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_EMAIL
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_PASSWORD
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_ROLE
import kotlinx.coroutines.delay

@OptIn(ExperimentalPermissionsApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ProfileEdit(
    modifier: Modifier = Modifier,
    stateUpload: UIState<String> = UIState.Empty,
    stateLocation: UIState<Address> = UIState.Empty,
    foto: String,
    akun: String,
    email: String,
    role: String,
    alamat: String,
    alamatLat: Double,
    alamatLon: Double,
    onSave: (ProfileRequest) -> Unit = {},
    onUpload: (Uri) -> Unit = {},
    onMarkerSearch: (Double, Double) -> Unit = { _, _ -> }
) {
    val locationPermissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    var newFoto by remember { mutableStateOf(foto) }
    var newEmail by remember { mutableStateOf(email) }
    var newPassword by remember { mutableStateOf("") }
    var newAlamat by remember { mutableStateOf(alamat) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }
    var newAlamatLocation by remember { mutableStateOf(LatLng(alamatLat, alamatLon)) }

    val scrollState = rememberScrollState()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(newAlamatLocation, 15f)
    }
    val dialogCameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(newAlamatLocation, 15f)
    }
    var columnScrollingEnabled by remember { mutableStateOf(true) }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                onUpload(uri)
            }
        }
    )

    var isMapsHidden by remember { mutableStateOf(true) }

    LaunchedEffect(cameraPositionState.isMoving) {
        if (!cameraPositionState.isMoving) {
            columnScrollingEnabled = true
        }
    }

    LaunchedEffect(Unit) {
        if (!locationPermissionState.allPermissionsGranted) {
            locationPermissionState.launchMultiplePermissionRequest()
        }
    }

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(scrollState, columnScrollingEnabled)
            .padding(bottom = 120.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ubah foto profil",
                modifier = modifier.clickable {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
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
        ProfileEditField(
            field = FIELD_TYPE_ALAMAT,
            placeholder = newAlamat,
            onAlamatClick = { isMapsHidden = false }
        ) { value, _ ->
            newAlamat = value
        }
        Spacer(modifier.size(20.dp))
        ProfileDetailMapLabel()
        Spacer(modifier.size(10.dp))
        ProfileEditMap(
            modifier = Modifier.pointerInteropFilter(
                onTouchEvent = {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            columnScrollingEnabled = false
                            false
                        }

                        else -> {
                            true
                        }
                    }
                }
            ),
            location = newAlamatLocation,
            cameraPositionState = cameraPositionState
        )
        Spacer(modifier.size(30.dp))
        ProfileEditSubmitButton(
            user = ProfileRequest(
                akun,
                newFoto,
                if (newEmail == "") email else newEmail,
                newPassword,
                if (newAlamat == "") alamat else newAlamat,
                if (newAlamatLocation.latitude == 0.0) alamatLat else newAlamatLocation.latitude,
                if (newAlamatLocation.longitude == 0.0) alamatLon else newAlamatLocation.longitude
            ),
            onSave = onSave
        )

        when (stateUpload) {
            is UIState.Success -> newFoto = stateUpload.data
            is UIState.Error -> {
                when (stateUpload.error) {
                    ERR_UNSUPPORTED -> {
                        // TODO: Error popup
                    }

                    else -> {}
                }
            }

            else -> {}
        }
    }

    AnimatedVisibility(
        visible = !isMapsHidden,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        LaunchedEffect(dialogCameraPositionState.position.target) {
            delay(500L)
            onMarkerSearch(
                dialogCameraPositionState.position.target.latitude,
                dialogCameraPositionState.position.target.longitude
            )
        }

        Dialog(
            onDismissRequest = { isMapsHidden = true },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            GoogleMap(
                modifier = modifier.fillMaxSize(),
                cameraPositionState = dialogCameraPositionState,
                properties = MapProperties(
                    isMyLocationEnabled = true,
                    mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
                        LocalContext.current, R.raw.maps
                    ),
                    mapType = MapType.NORMAL
                ),
                uiSettings = MapUiSettings(
                    mapToolbarEnabled = true,
                    myLocationButtonEnabled = true,
                    zoomControlsEnabled = false,
                    compassEnabled = true,
                    tiltGesturesEnabled = true
                ),
                onMapClick = {
                }
            ) {
                Marker(
                    state = MarkerState(
                        position = dialogCameraPositionState.position.target
                    ),
                    onClick = {
                        true
                    },
                )
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0xFFF7F7F7), RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                        )
                        .padding(vertical = 24.dp, horizontal = 20.dp)
                ) {
                    var address by remember { mutableStateOf(newAlamat) }

                    Text(
                        text = "Arahkan peta sesuai dengan alamat Anda",
                        style = TextStyle(
                            color = Color(0xFF000000),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontGilroy
                        )
                    )
                    Spacer(Modifier.height(20.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFD6F9F3), RoundedCornerShape(12.dp))
                            .padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.Top) {
                            when (stateLocation) {
                                is UIState.Success -> {
                                    address = stateLocation.data.getAddressLine(0)
                                    val shortAddress = address.split(", ")[0]

                                    Icon(
                                        painter = painterResource(id = dev.ikti.profile.R.drawable.ic_location),
                                        contentDescription = null,
                                        tint = Color.Unspecified
                                    )
                                    Spacer(Modifier.width(16.dp))
                                    Column {
                                        Spacer(Modifier.height(4.dp))
                                        Text(
                                            text = shortAddress,
                                            style = TextStyle(
                                                color = Color(0xFF000000),
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 18.sp,
                                                fontFamily = FontGilroy
                                            )
                                        )
                                        Spacer(Modifier.height(16.dp))
                                        Text(
                                            text = address,
                                            style = TextStyle(
                                                color = Color(0xFF000000),
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 16.sp,
                                                fontFamily = FontGilroy
                                            )
                                        )
                                    }
                                }

                                is UIState.Error -> {
                                    Text(
                                        text = "Gagal memuat alamat",
                                        style = TextStyle(
                                            color = Color(0xFF000000),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp,
                                            fontFamily = FontGilroy
                                        )
                                    )
                                }

                                else -> {
                                    Icon(
                                        painter = painterResource(id = dev.ikti.profile.R.drawable.ic_location),
                                        contentDescription = null,
                                        tint = Color.Unspecified
                                    )
                                    Spacer(Modifier.width(16.dp))
                                    Shimmer(
                                        height = 24.dp,
                                        width = 200.dp,
                                        shape = RoundedCornerShape(10.dp),
                                        color = Color(0xFF26B29D)
                                    )
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    Button(
                        onClick = {
                            if (stateLocation is UIState.Success) {
                                newAlamat = address
                                newAlamatLocation = LatLng(
                                    dialogCameraPositionState.position.target.latitude,
                                    dialogCameraPositionState.position.target.longitude
                                )

                                isMapsHidden = true
                            }
                        },
                        modifier = Modifier
                            .height(48.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonColors(
                            containerColor = Color(0xFF0A2D27),
                            contentColor = Color(0xFFACF2E7),
                            disabledContainerColor = Color(0xFF8A8A8E),
                            disabledContentColor = Color(0xFFFFFFFF)
                        )
                    ) {
                        Text(
                            text = "Simpan",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                fontFamily = FontGilroy
                            )
                        )
                    }
                }
            }
        }
    }
}
