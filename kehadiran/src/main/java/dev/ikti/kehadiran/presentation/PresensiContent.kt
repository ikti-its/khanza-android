package dev.ikti.kehadiran.presentation

import android.Manifest
import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.util.UIState
import dev.ikti.kehadiran.R
import dev.ikti.kehadiran.data.model.JadwalResponse
import dev.ikti.kehadiran.data.model.StatusPresensiResponse
import kotlinx.coroutines.delay

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PresensiContent(
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    pegawai: String,
    stateJadwal: UIState<JadwalResponse>,
    stateStatus: UIState<StatusPresensiResponse>,
    stateUpload: UIState<String>,
    getJadwal: (String) -> Unit,
    getStatus: (String) -> Unit,
    attend: (String, String, String) -> Unit,
    leave: (String, String, Boolean) -> Unit,
    upload: (CameraController) -> Unit,
    navController: NavHostController
) {
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    val cameraController = remember {
        LifecycleCameraController(context).apply {
            bindToLifecycle(lifecycleOwner)
        }
    }
    var showPlaceholder by remember { mutableStateOf(true) }
    var isAttendDialogHidden by remember { mutableStateOf(true) }
    var isLeaveDialogHidden by remember { mutableStateOf(true) }
    var id by remember { mutableStateOf("") }
    var jadwal by remember { mutableStateOf("") }
    var foto by remember { mutableStateOf("") }


    LaunchedEffect(pegawai) {
        if (pegawai != "") {
            getStatus(pegawai)
            getJadwal(pegawai)
        }
    }

    when (stateStatus) {
        is UIState.Success -> {
            id = stateStatus.data.id
            isLeaveDialogHidden = false
        }

        else -> {}
    }

    when (stateJadwal) {
        is UIState.Success -> {
            jadwal = stateJadwal.data.id
        }

        else -> {}
    }

    when (stateUpload) {
        is UIState.Success -> {
            foto = stateUpload.data
            isAttendDialogHidden = false
        }

        else -> {}
    }

    LaunchedEffect(Unit) {
        showPlaceholder = true
        delay(5000L)
        showPlaceholder = false
    }

    if (cameraPermissionState.status.isGranted) {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    PreviewView(ctx).apply {
                        scaleType = PreviewView.ScaleType.FILL_CENTER
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                        controller = cameraController

                        cameraController.cameraSelector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                            .build()
                    }
                },
                onRelease = {
                    cameraController.unbind()
                }
            )
            AnimatedVisibility(
                visible = showPlaceholder,
                enter = fadeIn(
                    animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
                ),
                exit = fadeOut(
                    animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TopAppBar(
                    modifier = Modifier.navigationBarsPadding(),
                    title = {
                        Text(
                            text = "Swafoto",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                                fontFamily = FontGilroy
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = null,
                                tint = Color(0xFFFFFFFF)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        navigationIconContentColor = Color(0xFFFFFFFF),
                        titleContentColor = Color(0xFFFFFFFF)
                    )
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .height(64.dp)
                            .width(64.dp)
                            .background(Color(0xFFFFFFFF), CircleShape)
                            .clip(CircleShape)
                            .clickable {
                                upload(cameraController)
                            }
                    )
                    Spacer(Modifier.height(48.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFC4C4C4), RoundedCornerShape(24.dp)),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .height(44.dp)
                                .width(183.dp),
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFC4C4C4),
                                contentColor = Color(0xFF535353)
                            )
                        ) {
                            Text(
                                text = "Face Recognition",
                                style = TextStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                maxLines = 1
                            )
                        }
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .height(44.dp)
                                .width(183.dp),
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFDFDFD),
                                contentColor = Color(0xFF272727)
                            )
                        ) {
                            Text(
                                text = "Swafoto",
                                style = TextStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                        }
                    }
                }
            }
        }
    } else {
        MainScaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.navigationBarsPadding(),
                    title = {
                        Text(
                            text = "Presensi",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                                fontFamily = FontGilroy
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }
                        ) {
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
            Text("Kamera tidak diizinkan")
        }
    }

    AnimatedVisibility(
        visible = !isAttendDialogHidden,
        enter = fadeIn(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        ),
        exit = fadeOut(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        )
    ) {
        Dialog(onDismissRequest = { navController.navigateUp() }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF7F7F7), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = { navController.navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = null,
                            tint = Color(0xFF272727)
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_review_setuju
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Konfirmasi Kehadiran",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            fontFamily = FontGilroy
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Apakah Anda yakin ingin melakukan presensi?",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontGilroy
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                if (foto != "") {
                    Spacer(Modifier.height(24.dp))
                    SubcomposeAsyncImage(
                        model = foto,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(180.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
                Spacer(Modifier.height(36.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = {
                            navController.navigateUp()
                        },
                        modifier = Modifier
                            .height(48.dp)
                            .width(135.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Unspecified,
                            contentColor = Color(0xFF272727)
                        ),
                        border = BorderStroke(1.dp, Color(0xFFE5E7EB))
                    ) {
                        Text(
                            text = "Batal",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                fontFamily = FontGilroy
                            )
                        )
                    }
                    Button(
                        onClick = {
                            if (pegawai != "" && jadwal != "" && foto != "") {
                                attend(pegawai, jadwal, foto)
                                navController.navigateUp()
                            }
                        },
                        modifier = Modifier
                            .height(48.dp)
                            .width(135.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0A2D27),
                            contentColor = Color(0xFFACF2E7)
                        )
                    ) {
                        Text(
                            text = "Absen",
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

    AnimatedVisibility(
        visible = !isLeaveDialogHidden,
        enter = fadeIn(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        ),
        exit = fadeOut(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        )
    ) {
        var emergency by remember { mutableStateOf(false) }

        Dialog(onDismissRequest = { navController.navigateUp() }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF7F7F7), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_review_setuju
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Konfirmasi Kepulangan",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            fontFamily = FontGilroy
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Apakah Anda yakin ingin mencatat kepulangan?",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontGilroy
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Hadir untuk keadaan darurat?",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontGilroy
                        )
                    )
                    Spacer(Modifier.width(16.dp))
                    Switch(
                        checked = emergency,
                        onCheckedChange = {
                            emergency = it
                        },
                        thumbContent = if (emergency) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(SwitchDefaults.IconSize),
                                )
                            }
                        } else {
                            null
                        },
                        colors = SwitchDefaults.colors(
                            checkedIconColor = Color(0xFF0A2D27),
                            checkedThumbColor = Color(0xFFF7F7F7),
                            checkedTrackColor = Color(0xFF0A2D27),
                            uncheckedIconColor = Color(0xFFC4C4C4),
                            uncheckedThumbColor = Color(0xFFF7F7F7),
                            uncheckedTrackColor = Color(0xFFC4C4C4),
                        )
                    )
                }
                Spacer(Modifier.height(36.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = {
                            navController.navigateUp()
                        },
                        modifier = Modifier
                            .height(48.dp)
                            .width(135.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Unspecified,
                            contentColor = Color(0xFF272727)
                        ),
                        border = BorderStroke(1.dp, Color(0xFFE5E7EB))
                    ) {
                        Text(
                            text = "Batal",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                fontFamily = FontGilroy
                            )
                        )
                    }
                    Button(
                        onClick = {
                            if (id != "" && pegawai != "") {
                                leave(id, pegawai, emergency)
                                navController.navigateUp()
                            }
                        },
                        modifier = Modifier
                            .height(48.dp)
                            .width(135.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0A2D27),
                            contentColor = Color(0xFFACF2E7)
                        )
                    ) {
                        Text(
                            text = "Pulang",
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
