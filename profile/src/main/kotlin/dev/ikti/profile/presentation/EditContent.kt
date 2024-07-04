package dev.ikti.profile.presentation

import android.Manifest
import android.content.Context
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
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
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.presentation.component.Shimmer
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.util.NetworkConstant
import dev.ikti.core.util.UIState
import dev.ikti.core.util.showToast
import dev.ikti.profile.data.model.ProfileRequest
import dev.ikti.profile.presentation.component.molecule.ProfileEditMap
import dev.ikti.profile.util.ProfileConstant.MAXIMUM_PASSWORD_LENGTH
import dev.ikti.profile.util.ProfileConstant.MINIMUM_PASSWORD_LENGTH
import dev.ikti.profile.util.validateEmail
import dev.ikti.profile.util.validateInput
import kotlinx.coroutines.delay

@OptIn(ExperimentalPermissionsApi::class, ExperimentalComposeUiApi::class)
@Composable
fun EditContent(
    context: Context = LocalContext.current,
    stateProfile: UIState<UserInfo>,
    stateEdit: UIState<Unit>,
    stateUpload: UIState<String>,
    stateLocation: UIState<Address>,
    onSave: (ProfileRequest) -> Unit,
    onUpload: (Uri) -> Unit,
    onMarkerSearch: (Double, Double) -> Unit,
    navController: NavHostController
) {
    val locationPermissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    LaunchedEffect(Unit) {
        if (!locationPermissionState.allPermissionsGranted) {
            locationPermissionState.launchMultiplePermissionRequest()
        }
    }

    val scrollState = rememberScrollState()
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

    MainScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ubah Profil",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            fontFamily = FontGilroy
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null,
                            tint = Color(0xFFFFFFFF)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0A2D27),
                    navigationIconContentColor = Color(0xFFFFFFFF),
                    titleContentColor = Color(0xFFFFFFFF)
                )
            )
        },
        background = Color(0xFFF7F7F7)
    ) {
        when (stateProfile) {
            is UIState.Success -> {
                val profile = stateProfile.data

                var newFoto by remember { mutableStateOf(profile.foto) }
                var newEmail by remember { mutableStateOf(profile.email) }
                var newPassword by remember { mutableStateOf("") }
                var newAlamat by remember { mutableStateOf(profile.alamat) }
                var isEmailError by remember { mutableStateOf(false) }
                var isPasswordError by remember { mutableStateOf(false) }
                var isPasswordHidden by remember { mutableStateOf(true) }
                var newAlamatLocation by remember {
                    mutableStateOf(
                        LatLng(
                            profile.alamatLat,
                            profile.alamatLon
                        )
                    )
                }

                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(newAlamatLocation, 15f)
                }
                val dialogCameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(newAlamatLocation, 15f)
                }

                LaunchedEffect(cameraPositionState.isMoving) {
                    if (!cameraPositionState.isMoving) {
                        columnScrollingEnabled = true
                    }
                }

                LaunchedEffect(newAlamatLocation) {
                    cameraPositionState.position =
                        CameraPosition.fromLatLngZoom(newAlamatLocation, 15f)
                }

                var isUploadToastShown = false

                LaunchedEffect(stateUpload) {
                    when (stateUpload) {
                        is UIState.Success -> {
                            newFoto = stateUpload.data
                            if (!isUploadToastShown) {
                                showToast(context, "Berhasil mengunggah gambar")
                                isUploadToastShown = true
                            }
                        }

                        is UIState.Error -> {
                            when (stateUpload.error) {
                                NetworkConstant.ERR_FILE_UNSUPPORTED -> {
                                    if (!isUploadToastShown) {
                                        showToast(context, "Gambar tidak kompatibel")
                                        isUploadToastShown = true
                                    }
                                }

                                else -> {
                                    if (!isUploadToastShown) {
                                        showToast(context, "Ukuran terlalu besar")
                                        isUploadToastShown = true
                                    }
                                }
                            }
                        }

                        UIState.Loading -> showToast(context, "Mengunggah gambar...")

                        else -> {}
                    }
                    isUploadToastShown = false
                }

                var isEditToastShown = false

                LaunchedEffect(stateEdit) {
                    when (stateEdit) {
                        is UIState.Success -> {
                            if (!isEditToastShown) {
                                showToast(context, "Berhasil mengubah data")
                                isEditToastShown = true
                            }
                        }

                        is UIState.Error -> {
                            if (!isEditToastShown) {
                                showToast(context, "Gagal mengubah data")
                                isEditToastShown = true
                            }
                        }

                        UIState.Loading -> showToast(context, "Mengubah data...")

                        else -> {}
                    }
                    isEditToastShown = false
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp)
                                    .background(Color(0xFF0A2D27))
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp)
                                    .background(Color(0xFFACF2E7))
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(scrollState, columnScrollingEnabled)
                                .padding(bottom = 140.dp)
                                .padding(horizontal = 24.dp)
                        ) {
                            Spacer(Modifier.height(30.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                        .clickable {
                                            singlePhotoPickerLauncher.launch(
                                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                            )
                                        },
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Ubah foto profil",
                                        style = TextStyle(
                                            color = Color(0xFF272727),
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 14.sp,
                                            fontFamily = FontGilroy
                                        )
                                    )
                                }
                            }
                            Spacer(Modifier.height(20.dp))
                            Column {
                                Text(
                                    text = "Email",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = newEmail,
                                    onValueChange = { input ->
                                        newEmail = input
                                        isEmailError = validateEmail(input)
                                    },
                                    textStyle = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        fontFamily = FontGilroy
                                    ),
                                    modifier = Modifier
                                        .height(48.dp)
                                        .fillMaxWidth(),
                                    enabled = true,
                                    isError = isEmailError,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                    singleLine = true,
                                    shape = RoundedCornerShape(8.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedTextColor = Color(0xFF272727),
                                        focusedTextColor = Color(0xFF272727),
                                        unfocusedBorderColor = Color(0xFFDCDCDC),
                                        focusedBorderColor = Color(0xFFDCDCDC),
                                        unfocusedContainerColor = Color(0xFFF7F7F7),
                                        focusedContainerColor = Color(0xFFF7F7F7)
                                    )
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            Column {
                                Text(
                                    text = "Kata sandi",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = newPassword,
                                    onValueChange = { input ->
                                        newPassword = input
                                        isPasswordError = validateInput(
                                            input,
                                            MINIMUM_PASSWORD_LENGTH,
                                            MAXIMUM_PASSWORD_LENGTH
                                        )
                                    },
                                    textStyle = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        fontFamily = FontGilroy
                                    ),
                                    modifier = Modifier
                                        .height(48.dp)
                                        .fillMaxWidth(),
                                    enabled = true,
                                    trailingIcon = {
                                        IconButton(
                                            onClick = {
                                                isPasswordHidden = !isPasswordHidden
                                            }
                                        ) {
                                            Icon(
                                                painter = if (isPasswordHidden) {
                                                    painterResource(id = dev.ikti.profile.R.drawable.ic_filled_eye)
                                                } else {
                                                    painterResource(id = dev.ikti.profile.R.drawable.ic_outlined_eye)
                                                },
                                                contentDescription = null,
                                                tint = Color(0xFFC4C4C4)
                                            )
                                        }
                                    },
                                    isError = isPasswordError,
                                    visualTransformation = if (isPasswordHidden) {
                                        PasswordVisualTransformation()
                                    } else {
                                        VisualTransformation.None
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    singleLine = true,
                                    shape = RoundedCornerShape(8.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedTextColor = Color(0xFF272727),
                                        focusedTextColor = Color(0xFF272727),
                                        unfocusedBorderColor = Color(0xFFDCDCDC),
                                        focusedBorderColor = Color(0xFFDCDCDC),
                                        unfocusedContainerColor = Color(0xFFF7F7F7),
                                        focusedContainerColor = Color(0xFFF7F7F7)
                                    )
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            Column {
                                Text(
                                    text = "Role",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(8.dp))
                                TextField(
                                    value = profile.role,
                                    onValueChange = {},
                                    textStyle = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        fontFamily = FontGilroy
                                    ),
                                    modifier = Modifier
                                        .height(48.dp)
                                        .fillMaxWidth(),
                                    enabled = false,
                                    shape = RoundedCornerShape(8.dp),
                                    colors = TextFieldDefaults.colors(
                                        disabledTextColor = Color(0xFF272727),
                                        disabledContainerColor = Color(0xFFF0F0F0),
                                        disabledIndicatorColor = Color(0xFFF0F0F0)
                                    )
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            Column {
                                Text(
                                    text = "Alamat Lengkap",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(8.dp))
                                TextField(
                                    value = newAlamat,
                                    onValueChange = {},
                                    textStyle = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        fontFamily = FontGilroy
                                    ),
                                    modifier = Modifier
                                        .heightIn(min = 48.dp)
                                        .fillMaxWidth()
                                        .clickable {
                                            isMapsHidden = false
                                        },
                                    enabled = false,
                                    shape = RoundedCornerShape(8.dp),
                                    colors = TextFieldDefaults.colors(
                                        disabledTextColor = Color(0xFF272727),
                                        disabledContainerColor = Color(0xFFF0F0F0),
                                        disabledIndicatorColor = Color(0xFFF0F0F0)
                                    )
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = "Denah Lokasi",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
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
                                alamat = profile.alamat,
                                location = newAlamatLocation,
                                cameraPositionState = cameraPositionState,
                                showMap = {
                                    isMapsHidden = false
                                }
                            )
                            Spacer(Modifier.height(30.dp))
                            Button(
                                onClick = {
                                    val user = ProfileRequest(
                                        profile.akun,
                                        newFoto,
                                        if (newEmail == "") profile.email else newEmail,
                                        newPassword,
                                        if (newAlamat == "") profile.alamat else newAlamat,
                                        if (newAlamatLocation.latitude == 0.0) profile.alamatLat else newAlamatLocation.latitude,
                                        if (newAlamatLocation.longitude == 0.0) profile.alamatLon else newAlamatLocation.longitude
                                    )

                                    onSave(user)
                                },
                                modifier = Modifier
                                    .height(48.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(30.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF0A2D27),
                                    contentColor = Color(0xFFACF2E7)
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .background(color = Color(0xFFF7F7F7), shape = CircleShape)
                            )
                            SubcomposeAsyncImage(
                                model = newFoto,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(CircleShape)
                            )

                        }
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
                            modifier = Modifier.fillMaxSize(),
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
                                        Color(0xFFF7F7F7),
                                        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
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
                                Spacer(
                                    Modifier
                                        .height(2.dp)
                                        .fillMaxWidth()
                                        .background(Color(0xFFF1F1F1))
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
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF0A2D27),
                                        contentColor = Color(0xFFACF2E7)
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

            is UIState.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp)
                                    .background(Color(0xFF0A2D27))
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp)
                                    .background(Color(0xFFACF2E7))
                            )
                        }
                        Spacer(Modifier.height(30.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 120.dp)
                                .padding(horizontal = 24.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    modifier = Modifier.padding(
                                        horizontal = 8.dp,
                                        vertical = 4.dp
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Shimmer(
                                        height = 16.dp,
                                        width = 100.dp,
                                        shape = RoundedCornerShape(8.dp),
                                        color = Color(0xFF272727)
                                    )
                                }
                            }
                            Spacer(Modifier.height(20.dp))
                            Column {
                                Text(
                                    text = "Email",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(8.dp))
                                Shimmer(
                                    height = 48.dp,
                                    width = 370.dp,
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF272727)
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            Column {
                                Text(
                                    text = "Kata sandi",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(8.dp))
                                Shimmer(
                                    height = 48.dp,
                                    width = 370.dp,
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF272727)
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            Column {
                                Text(
                                    text = "Role",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(8.dp))
                                Shimmer(
                                    height = 48.dp,
                                    width = 370.dp,
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF272727)
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            Column {
                                Text(
                                    text = "Alamat Lengkap",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(8.dp))
                                Shimmer(
                                    height = 48.dp,
                                    width = 370.dp,
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF272727)
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = "Denah Lokasi",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 200.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .background(color = Color(0xFFF7F7F7), shape = CircleShape)
                            )
                            Shimmer(72.dp, 72.dp, CircleShape, Color(0xFF272727))
                        }
                    }
                }
                showToast(context, "Gagal memuat profil")
            }

            else -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp)
                                    .background(Color(0xFF0A2D27))
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp)
                                    .background(Color(0xFFACF2E7))
                            )
                        }
                        Spacer(Modifier.height(30.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 120.dp)
                                .padding(horizontal = 24.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    modifier = Modifier.padding(
                                        horizontal = 8.dp,
                                        vertical = 4.dp
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Shimmer(
                                        height = 16.dp,
                                        width = 100.dp,
                                        shape = RoundedCornerShape(8.dp),
                                        color = Color(0xFF272727)
                                    )
                                }
                            }
                            Spacer(Modifier.height(20.dp))
                            Column {
                                Text(
                                    text = "Email",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(8.dp))
                                Shimmer(
                                    height = 48.dp,
                                    width = 370.dp,
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF272727)
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            Column {
                                Text(
                                    text = "Kata sandi",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(8.dp))
                                Shimmer(
                                    height = 48.dp,
                                    width = 370.dp,
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF272727)
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            Column {
                                Text(
                                    text = "Role",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(8.dp))
                                Shimmer(
                                    height = 48.dp,
                                    width = 370.dp,
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF272727)
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            Column {
                                Text(
                                    text = "Alamat Lengkap",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(8.dp))
                                Shimmer(
                                    height = 48.dp,
                                    width = 370.dp,
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF272727)
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = "Denah Lokasi",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 200.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .background(color = Color(0xFFF7F7F7), shape = CircleShape)
                            )
                            Shimmer(72.dp, 72.dp, CircleShape, Color(0xFF272727))
                        }
                    }
                }
            }
        }
    }
}
