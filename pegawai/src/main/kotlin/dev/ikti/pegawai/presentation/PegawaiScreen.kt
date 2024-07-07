package dev.ikti.pegawai.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun PegawaiScreen(
    role: String,
    feature: String,
    viewModel: PegawaiViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val token by viewModel.token.collectAsState()
    val stateData by viewModel.stateData.collectAsState()
    val stateKetersediaan by viewModel.stateKetersediaan.collectAsState()
    val stateLokasi by viewModel.stateLokasi.collectAsState()

    when (feature) {
        "View" -> {
            ViewContent(
                role = role,
                navController = navController
            )
        }

        "Data" -> {
            DataContent(
                token = token,
                stateData = stateData,
                getData = { viewModel.getPegawai(token) },
                navController = navController
            )
        }

        "Daftar" -> {
            DaftarContent(
                token = token,
                stateKetersediaan = stateKetersediaan,
                stateLokasi = stateLokasi,
                getLokasi = { viewModel.getLokasi(it) },
                onQuery = { query, latitude, longitude ->
                    viewModel.queryKetersediaan(
                        token,
                        query,
                        latitude,
                        longitude
                    )
                },
                navController = navController
            )
        }
    }
}