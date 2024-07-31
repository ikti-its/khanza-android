package dev.ikti.kehadiran.presentation

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import dev.ikti.core.presentation.component.Shimmer
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.util.UIState
import dev.ikti.core.util.showToast
import dev.ikti.kehadiran.R
import dev.ikti.kehadiran.data.model.TukarRequest
import dev.ikti.kehadiran.domain.model.DaftarTukar
import dev.ikti.kehadiran.presentation.component.TinjauTukarCard

@Composable
fun TinjauTukarContent(
    context: Context = LocalContext.current,
    pegawai: String,
    stateTinjauTukar: UIState<List<DaftarTukar>>,
    stateUpdateTukar: UIState<Unit>,
    getData: (String) -> Unit,
    updateStatus: (String, TukarRequest) -> Unit,
    navController: NavHostController
) {
    val lazyListState = rememberLazyListState()
    var isDialogHidden by remember { mutableStateOf(true) }
    var isDataRefreshed by remember { mutableStateOf(false) }
    var reviewType by remember { mutableStateOf(true) }
    var id by remember { mutableStateOf("") }
    var sender by remember { mutableStateOf("") }
    var recipient by remember { mutableStateOf("") }
    var hari by remember { mutableIntStateOf(0) }
    var senderShift by remember { mutableStateOf("") }
    var recipientShift by remember { mutableStateOf("") }

    LaunchedEffect(pegawai) {
        getData(pegawai)
    }

    MainScaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.navigationBarsPadding(),
                title = {
                    Text(
                        text = "Peninjauan Penukaran",
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
                when (stateTinjauTukar) {
                    is UIState.Success -> {
                        val tukar = stateTinjauTukar.data

                        if (tukar.isNotEmpty()) {
                            Spacer(Modifier.height(24.dp))
                            LazyColumn(state = lazyListState) {
                                items(items = tukar) { tinjauan ->
                                    TinjauTukarCard(
                                        tinjauan = tinjauan,
                                        showDialog = { review, data ->
                                            reviewType = review

                                            id = data.id
                                            sender = data.sender
                                            recipient = data.recipient
                                            hari = data.hari
                                            senderShift = data.senderShift
                                            recipientShift = data.recipientShift

                                            isDialogHidden = false
                                        }
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
                                    painter = painterResource(id = R.drawable.ic_kehadiran_status),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(54.dp)
                                )
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    text = "Peninjauan Penukaran",
                                    style = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 20.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    text = "Belum ada penukaran yang perlu ditinjau",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                            }
                        }
                    }

                    is UIState.Error -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_kehadiran_riwayat),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(54.dp)
                            )
                            Spacer(Modifier.height(16.dp))
                            Text(
                                text = "Peninjauan Penukaran",
                                style = TextStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(12.dp))
                            Text(
                                text = "Gagal memuat daftar penukaran",
                                style = TextStyle(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                        }
                    }

                    else -> {
                        Spacer(Modifier.height(24.dp))
                        repeat(3) {
                            Shimmer(
                                height = 130.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFF272727)
                            )
                            Spacer(Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }

    AnimatedVisibility(
        visible = !isDialogHidden,
        enter = fadeIn(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        ),
        exit = fadeOut(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        )
    ) {
        val onDismiss = { isDialogHidden = true }

        Dialog(onDismissRequest = { onDismiss() }) {
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
                    IconButton(onClick = { onDismiss() }
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
                    if (reviewType) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_review_setuju
                            ),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    } else {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_review_tolak
                            ),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (reviewType) {
                        Text(
                            text = "Menyetujui Pengajuan Tukar",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                fontFamily = FontGilroy
                            ),
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Text(
                            text = "Menolak Pengajuan Tukar",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                fontFamily = FontGilroy
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (reviewType) {
                        Text(
                            text = "Apakah Anda yakin untuk menyetujui pengajuan tukar?",
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                fontFamily = FontGilroy
                            ),
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Text(
                            text = "Apakah Anda yakin untuk menolak pengajuan tukar?",
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                fontFamily = FontGilroy
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(Modifier.height(36.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (reviewType) {
                        Row(modifier = Modifier.fillMaxWidth(0.5f)) {
                            OutlinedButton(
                                onClick = {
                                    onDismiss()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
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
                            Spacer(Modifier.width(8.dp))
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Spacer(Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    if (id != "") {
                                        updateStatus(
                                            id,
                                            TukarRequest(
                                                sender,
                                                recipient,
                                                hari,
                                                senderShift,
                                                recipientShift,
                                                "Diterima"
                                            )
                                        )
                                        onDismiss()
                                        isDataRefreshed = false
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(30.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF0A2D27),
                                    contentColor = Color(0xFFACF2E7)
                                )
                            ) {
                                Text(
                                    text = "Setuju",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                            }
                        }
                    } else {
                        Row(modifier = Modifier.fillMaxWidth(0.5f)) {
                            Button(
                                onClick = {
                                    if (id != "") {
                                        updateStatus(
                                            id,
                                            TukarRequest(
                                                sender,
                                                recipient,
                                                hari,
                                                senderShift,
                                                recipientShift,
                                                "Ditolak"
                                            )
                                        )
                                        onDismiss()
                                        isDataRefreshed = false
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(30.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFDA4141),
                                    contentColor = Color(0xFFFFFFFF)
                                )
                            ) {
                                Text(
                                    text = "Tolak",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                            }
                            Spacer(Modifier.width(8.dp))
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Spacer(Modifier.width(8.dp))
                            OutlinedButton(
                                onClick = {
                                    onDismiss()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
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
                        }
                    }
                }
            }
        }
    }

    when (stateUpdateTukar) {
        is UIState.Success -> {
            if (!isDataRefreshed) {
                showToast(context, "Berhasil mengubah status")
                getData(pegawai)
                isDataRefreshed = true
            }
        }

        is UIState.Error -> {
            showToast(context, "Gagal mengubah status")
        }

        else -> {}
    }
}
