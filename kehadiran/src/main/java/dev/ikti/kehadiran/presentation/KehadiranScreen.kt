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
    val statePengajuan by pengajuanViewModel.statePengajuan.collectAsState()

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

        "Jadwal" -> {}

        "Riwayat" -> {}

        "Pengajuan" -> {
            PengajuanContent(
                pegawai = pegawai,
                statePengajuan = statePengajuan,
                getPegawai = {
                    viewModel.getLocalUser(token)
                },
                createAjuan = { ajuan ->
                    pengajuanViewModel.createPengajuan(token, ajuan)
                },
                navController = navController
            )
        }

        "Peninjauan" -> {}
    }
}