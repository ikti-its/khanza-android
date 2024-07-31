package dev.ikti.kehadiran.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.util.SetSystemUI

@Composable
fun KehadiranScreen(
    role: String,
    feature: String,
    viewModel: KehadiranViewModel = hiltViewModel(),
    pengajuanViewModel: PengajuanViewModel = hiltViewModel(),
    presensiViewModel: PresensiViewModel = hiltViewModel(),
    faceViewModel: FaceViewModel = hiltViewModel(),
    tukarViewModel: TukarViewModel = hiltViewModel(),
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
    val statePresensiJadwal by presensiViewModel.stateJadwal.collectAsState()
    val statePresensiStatus by presensiViewModel.stateStatus.collectAsState()
    val stateUpload by presensiViewModel.stateUpload.collectAsState()
    val stateAttend by presensiViewModel.stateAttend.collectAsState()
    val stateLeave by presensiViewModel.stateLeave.collectAsState()
    val stateLokasi by presensiViewModel.stateLokasi.collectAsState()
    val dataBitmap by faceViewModel.dataBitmap.collectAsState()

    val stateTukar by tukarViewModel.stateTukar.collectAsState()
    val stateTukarJadwal by tukarViewModel.stateTukarJadwal.collectAsState()
    val stateTukarPegawai by tukarViewModel.stateTukarPegawai.collectAsState()
    val stateStatusTukar by tukarViewModel.stateStatusTukar.collectAsState()
    val stateTinjauTukar by tukarViewModel.stateTinjauTukar.collectAsState()
    val stateUpdateTukar by tukarViewModel.stateUpdateTukar.collectAsState()

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

        "Presensi" -> {
            SetSystemUI(fullScreen = true)
            PresensiContent(
                pegawai = pegawai,
                dataBitmap = dataBitmap,
                stateAttend = stateAttend,
                stateLeave = stateLeave,
                stateJadwal = statePresensiJadwal,
                stateStatus = statePresensiStatus,
                stateUpload = stateUpload,
                stateLokasi = stateLokasi,
                getStatus = { presensiViewModel.getStatus(token, it) },
                getJadwal = { presensiViewModel.getJadwal(token, it) },
                attend = { idPegawai, jadwal, foto ->
                    presensiViewModel.attend(token, idPegawai, jadwal, foto)
                },
                attendKode = { idPegawai, jadwal, foto, kode ->
                    presensiViewModel.attendKode(token, idPegawai, jadwal, foto, kode)
                },
                leave = { id, idPegawai, emergency ->
                    presensiViewModel.leave(token, id, idPegawai, emergency)
                },
                upload = { controller ->
                    presensiViewModel.uploadSwafoto(token, controller)
                },
                getLokasi = { presensiViewModel.getLokasi(token) },
                getFoto = { faceViewModel.getFoto(token, it) },
                navController = navController
            )
        }

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

        "Tukar" -> {
            TukarContent(
                pegawai = pegawai,
                stateTukar = stateTukar,
                stateTukarJadwal = stateTukarJadwal,
                stateTukarPegawai = stateTukarPegawai,
                getJadwal = { id, hari -> tukarViewModel.getJadwal(token, id, hari) },
                getPegawai = { hari -> tukarViewModel.getPegawai(token, hari) },
                createTukar = { tukar -> tukarViewModel.createTukar(token, tukar) },
                navController = navController
            )
        }

        "StatusTukar" -> {
            StatusTukarContent(
                pegawai = pegawai,
                stateStatusTukar = stateStatusTukar,
                getData = { id ->
                    tukarViewModel.getSender(token, id)
                },
                navController = navController
            )
        }

        "TinjauTukar" -> {
            TinjauTukarContent(
                pegawai = pegawai,
                stateTinjauTukar = stateTinjauTukar,
                stateUpdateTukar = stateUpdateTukar,
                getData = { id ->
                    tukarViewModel.getRecipient(token, id)
                },
                updateStatus = { id, tukar -> tukarViewModel.updateStatus(token, id, tukar) },
                navController = navController
            )
        }
    }
}