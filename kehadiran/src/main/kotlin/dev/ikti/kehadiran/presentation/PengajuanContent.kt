package dev.ikti.kehadiran.presentation

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.util.UIState
import dev.ikti.core.util.convertMillisToDate
import dev.ikti.core.util.formatDateString
import dev.ikti.core.util.showToast
import dev.ikti.kehadiran.data.model.PengajuanRequest
import dev.ikti.kehadiran.data.model.PengajuanResponse

@Composable
fun PengajuanContent(
    context: Context = LocalContext.current,
    pegawai: String,
    statePengajuan: UIState<PengajuanResponse>,
    createAjuan: (PengajuanRequest) -> Unit,
    navController: NavHostController
) {
    val dateRangePickerState = rememberDateRangePickerState()
    var isDateModalHidden by remember { mutableStateOf(true) }
    val selectedStartDate =
        dateRangePickerState.selectedStartDateMillis?.let { convertMillisToDate(it) } ?: ""
    val selectedEndDate =
        dateRangePickerState.selectedEndDateMillis?.let { convertMillisToDate(it) } ?: ""
    var awal by remember { mutableStateOf("") }
    var akhir by remember { mutableStateOf("") }

    val alasanList = listOf(
        "Sakit",
        "Izin",
        "Cuti Tahunan",
        "Cuti Besar",
        "Cuti Melahirkan",
        "Cuti Karena Alasan Penting"
    )
    var isAlasanExpanded by remember { mutableStateOf(false) }
    var alasan by remember { mutableStateOf(alasanList[0]) }

    var isPengajuanToastShown = false

    LaunchedEffect(statePengajuan) {
        when (statePengajuan) {
            is UIState.Success -> {
                if (!isPengajuanToastShown) {
                    navController.navigateUp()
                    showToast(context, "Berhasil mengajukan perizinan")
                    isPengajuanToastShown = true
                }
            }

            is UIState.Error -> {
                if (!isPengajuanToastShown) {
                    showToast(context, "Gagal mengajukan perizinan")
                    isPengajuanToastShown = true
                }
            }

            UIState.Loading -> {
                if (!isPengajuanToastShown) {
                    showToast(context, "Mengajukan perizinan...")
                    isPengajuanToastShown = true
                }
            }

            else -> {}
        }
        isPengajuanToastShown = false
    }

    MainScaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.navigationBarsPadding(),
                title = {
                    Text(
                        text = "Pengajuan Perizinan",
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
                    Text(
                        text = "Tanggal Mulai",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            fontFamily = FontGilroy
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = if (awal != "") formatDateString(awal) else awal,
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
                                isDateModalHidden = false
                            },
                        trailingIcon = {
                            Icon(imageVector = Icons.Rounded.DateRange, contentDescription = null)
                        },
                        enabled = false,
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledContainerColor = Color.Unspecified,
                            disabledBorderColor = Color(0xFFDCDCDC),
                            disabledTextColor = Color(0xFF535353)
                        )
                    )
                }
                Spacer(Modifier.height(20.dp))
                Column {
                    Text(
                        text = "Tanggal Selesai",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            fontFamily = FontGilroy
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = if (akhir != "") formatDateString(akhir) else akhir,
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
                                isDateModalHidden = false
                            },
                        trailingIcon = {
                            Icon(imageVector = Icons.Rounded.DateRange, contentDescription = null)
                        },
                        enabled = false,
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledContainerColor = Color.Unspecified,
                            disabledBorderColor = Color(0xFFDCDCDC),
                            disabledTextColor = Color(0xFF535353)
                        )
                    )
                }
                Spacer(Modifier.height(20.dp))
                Column {
                    val onExpand = { isAlasanExpanded = true }
                    val onDismiss = { isAlasanExpanded = false }
                    Text(
                        text = "Alasan",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            fontFamily = FontGilroy
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = alasan,
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
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isAlasanExpanded)
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledContainerColor = Color.Unspecified,
                            disabledBorderColor = Color(0xFFDCDCDC),
                            disabledTextColor = Color(0xFF535353)
                        )
                    )
                    DropdownMenu(
                        expanded = isAlasanExpanded,
                        onDismissRequest = { onDismiss() },
                        modifier = Modifier.requiredSizeIn(minWidth = 370.dp, maxHeight = 200.dp)
                    ) {
                        alasanList.forEach { option ->
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
                                    alasan = option
                                    onDismiss()
                                }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(30.dp))
                Button(
                    onClick = {
                        if (awal != "" && akhir != "") {
                            val ajuan = PengajuanRequest(
                                pegawai,
                                awal,
                                akhir,
                                when (alasan) {
                                    "Sakit" -> "S"
                                    "Izin" -> "I"
                                    "Cuti Tahunan" -> "CT"
                                    "Cuti Besar" -> "CB"
                                    "Cuti Melahirkan" -> "CM"
                                    "Cuti Karena Alasan Penting" -> "CU"
                                    else -> ""
                                },
                                "Diproses"
                            )
                            createAjuan(ajuan)
                        } else {
                            showToast(context, "Lengkapi terlebih dahulu!")
                        }
                    },
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFF0A2D27),
                        contentColor = Color(0xFFACF2E7)
                    )
                ) {
                    Text(
                        text = "Ajukan",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = FontGilroy
                        )
                    )
                }
                Spacer(Modifier.height(8.dp))
                OutlinedButton(
                    onClick = {
                        navController.navigateUp()
                    },
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Unspecified,
                        contentColor = Color(0xFF272727)
                    )
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

    AnimatedVisibility(
        visible = !isDateModalHidden,
        enter = fadeIn(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        ),
        exit = fadeOut(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        )
    ) {
        val onDismiss = { isDateModalHidden = true }
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF7F7F7), RoundedCornerShape(12.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { onDismiss() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = Color(0xFF272727)
                        )
                    }
                    IconButton(onClick = {
                        awal = selectedStartDate
                        akhir = selectedEndDate
                        onDismiss()
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Check,
                            contentDescription = null,
                            tint = Color(0xFF272727)
                        )
                    }
                }
                DateRangePicker(
                    state = dateRangePickerState,
                    colors = DatePickerDefaults.colors(
                        dayInSelectionRangeContainerColor = Color(0x500A2D27),
                        selectedDayContainerColor = Color(0xFF0A2D27),
                        selectedDayContentColor = Color(0xFFACF2E7)
                    )
                )
            }
        }
    }
}
