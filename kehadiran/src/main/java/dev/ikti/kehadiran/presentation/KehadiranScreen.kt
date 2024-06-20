package dev.ikti.kehadiran.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun KehadiranScreen(
    role: String,
    feature: String,
    viewModel: KehadiranViewModel = hiltViewModel(),
    pengajuanViewModel: PengajuanViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val token by viewModel.token.collectAsState()
    val pegawai by viewModel.pegawai.collectAsState()
    val stateJadwal by viewModel.stateJadwal.collectAsState()
    val stateRiwayat by viewModel.stateRiwayat.collectAsState()
    val statePengajuan by pengajuanViewModel.statePengajuan.collectAsState()
    val stateStatus by pengajuanViewModel.stateStatus.collectAsState()
    val statePeninjauanList by pengajuanViewModel.statePeninjauanList.collectAsState()
    val stateUpdate by pengajuanViewModel.stateUpdate.collectAsState()

    LaunchedEffect(token) {
        if (token != "") {
            viewModel.getLocalUser(token)
        }
    }

    when (feature) {
        "View" -> {
            ViewContent(
                role = role,
                navController = navController
            )
        }

        "Presensi" -> {}

        "Jadwal" -> {
            JadwalContent(
                pegawai = pegawai,
                stateJadwal = stateJadwal,
                getData = { viewModel.getJadwal(token, it) },
                navController = navController
            )
        }

        "Riwayat" -> {
            RiwayatContent(
                pegawai = pegawai,
                stateRiwayat = stateRiwayat,
                getData = { viewModel.getRiwayat(token, it) },
                navController = navController
            )
        }

        "Pengajuan" -> {
            PengajuanContent(
                pegawai = pegawai,
                statePengajuan = statePengajuan,
                createAjuan = { ajuan ->
                    pengajuanViewModel.createPengajuan(token, ajuan)
                },
                navController = navController
            )
        }

        "Status" -> {
            StatusContent(
                pegawai = pegawai,
                stateStatus = stateStatus,
                getData = { id ->
                    pengajuanViewModel.getByPegawaiId(token, id)
                },
                navController = navController
            )
        }

        "Peninjauan" -> {
            PeninjauanContent(
                token = token,
                statePeninjauanList = statePeninjauanList,
                stateUpdate = stateUpdate,
                getData = { pengajuanViewModel.getAll(it) },
                updateStatus = { id, ajuan -> pengajuanViewModel.updateStatus(token, id, ajuan) },
                navController = navController
            )
        }
    }
}