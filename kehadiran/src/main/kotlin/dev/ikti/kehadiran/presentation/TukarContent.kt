package dev.ikti.kehadiran.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.util.UIState
import dev.ikti.core.util.showToast
import dev.ikti.kehadiran.R
import dev.ikti.kehadiran.data.model.JadwalResponse
import dev.ikti.kehadiran.data.model.TukarRequest
import dev.ikti.kehadiran.data.model.TukarResponse
import dev.ikti.kehadiran.domain.model.DaftarJadwal
import dev.ikti.kehadiran.presentation.component.TukarCard

@Composable
fun TukarContent(
    context: Context = LocalContext.current,
    pegawai: String,
    stateTukar: UIState<TukarResponse>,
    stateTukarJadwal: UIState<JadwalResponse>,
    stateTukarPegawai: UIState<List<DaftarJadwal>>,
    createTukar: (TukarRequest) -> Unit,
    getJadwal: (String, Int) -> Unit,
    getPegawai: (Int) -> Unit,
    navController: NavHostController
) {
    val lazyListState = rememberLazyListState()

    val hariList = listOf(
        "Pilih Hari",
        "Senin",
        "Selasa",
        "Rabu",
        "Kamis",
        "Jumat",
        "Sabtu",
        "Minggu"
    )
    var isHariExpanded by remember { mutableStateOf(false) }
    var hari by remember { mutableStateOf(hariList[0]) }
    var senderId by remember { mutableStateOf("") }
    var senderShift by remember { mutableStateOf("") }

    var isTukarToastShown = false

    LaunchedEffect(stateTukar) {
        when (stateTukar) {
            is UIState.Success -> {
                if (!isTukarToastShown) {
                    navController.navigateUp()
                    showToast(context, "Berhasil mengajukan penukaran")
                    isTukarToastShown = true
                }
            }

            is UIState.Error -> {
                if (!isTukarToastShown) {
                    showToast(context, "Gagal mengajukan penukaran")
                    isTukarToastShown = true
                }
            }

            UIState.Loading -> {
                if (!isTukarToastShown) {
                    showToast(context, "Mengajukan penukaran...")
                    isTukarToastShown = true
                }
            }

            else -> {}
        }
        isTukarToastShown = false
    }

    LaunchedEffect(stateTukarJadwal) {
        when (stateTukarJadwal) {
            is UIState.Success -> {
                senderId = stateTukarJadwal.data.pegawai
                senderShift = stateTukarJadwal.data.shift
            }

            else -> {}
        }
    }

    LaunchedEffect(hari) {
        val hariId = when (hari) {
            "Senin" -> 1
            "Selasa" -> 2
            "Rabu" -> 3
            "Kamis" -> 4
            "Jumat" -> 5
            "Sabtu" -> 6
            "Minggu" -> 7
            else -> 0
        }
        getPegawai(hariId)
        getJadwal(pegawai, hariId)
    }

    MainScaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.navigationBarsPadding(),
                title = {
                    Text(
                        text = "Pengajuan Penukaran",
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
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(Color(0xFFACF2E7))
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(Modifier.height(24.dp))
                Column {
                    val onExpand = { isHariExpanded = true }
                    val onDismiss = { isHariExpanded = false }
                    Text(
                        text = "Hari",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            fontFamily = FontGilroy
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = hari,
                        onValueChange = {},
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            fontFamily = FontGilroy
                        ),
                        modifier = Modifier
                            .height(48.dp)
                            .fillMaxWidth()
                            .clickable {
                                onExpand()
                            },
                        enabled = false,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isHariExpanded)
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledContainerColor = Color.Unspecified,
                            disabledBorderColor = Color(0xFFDCDCDC),
                            disabledTextColor = Color(0xFF535353)
                        )
                    )
                    DropdownMenu(
                        expanded = isHariExpanded,
                        onDismissRequest = { onDismiss() },
                        modifier = Modifier.requiredSizeIn(minWidth = 370.dp, maxHeight = 200.dp)
                    ) {
                        hariList.forEach { option ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = option,
                                        style = TextStyle(
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 16.sp,
                                            fontFamily = FontGilroy
                                        )
                                    )
                                },
                                onClick = {
                                    hari = option
                                    onDismiss()
                                }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
                when (stateTukarPegawai) {
                    is UIState.Success -> {
                        val tukar = stateTukarPegawai.data

                        if (tukar.isNotEmpty()) {
                            Spacer(Modifier.height(24.dp))
                            LazyColumn(state = lazyListState) {
                                items(items = tukar) { jadwal ->
                                    val hariId = when (hari) {
                                        "Senin" -> 1
                                        "Selasa" -> 2
                                        "Rabu" -> 3
                                        "Kamis" -> 4
                                        "Jumat" -> 5
                                        "Sabtu" -> 6
                                        else -> 7
                                    }

                                    TukarCard(
                                        daftar = jadwal,
                                        hari = hariId,
                                        senderId = senderId,
                                        senderShift = senderShift,
                                        onClick = { req -> createTukar(req) }
                                    )
                                    Spacer(Modifier.height(16.dp))
                                }
                            }
                        } else {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_kehadiran_pengajuan),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(54.dp)
                                )
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    text = "Pengajuan Penukaran",
                                    style = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 20.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    text = "Belum ada jadwal pegawai",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}
