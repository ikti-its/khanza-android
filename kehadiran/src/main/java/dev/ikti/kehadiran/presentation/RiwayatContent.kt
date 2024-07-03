package dev.ikti.kehadiran.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import dev.ikti.core.presentation.component.Shimmer
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.util.UIState
import dev.ikti.core.util.showToast
import dev.ikti.kehadiran.R
import dev.ikti.kehadiran.data.model.PresensiResponse
import dev.ikti.kehadiran.presentation.component.RiwayatCard

@Composable
fun RiwayatContent(
    context: Context = LocalContext.current,
    pegawai: String,
    stateRiwayat: UIState<List<PresensiResponse>>,
    getData: (String) -> Unit,
    navController: NavHostController
) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(pegawai) {
        if (pegawai != "") {
            getData(pegawai)
        }
    }

    MainScaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.navigationBarsPadding(),
                title = {
                    Text(
                        text = "Riwayat Kehadiran",
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
                when (stateRiwayat) {
                    is UIState.Success -> {
                        val riwayat = stateRiwayat.data

                        if (riwayat.isNotEmpty()) {
                            Spacer(Modifier.height(24.dp))
                            Text(
                                text = "Catatan Kehadiran",
                                style = TextStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
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
                            LazyColumn(state = lazyListState) {
                                items(items = riwayat) { riwayat ->
                                    RiwayatCard(riwayat = riwayat)
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
                                    painter = painterResource(id = R.drawable.ic_kehadiran_riwayat),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(54.dp)
                                )
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    text = "Riwayat Kehadiran",
                                    style = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 20.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    text = "Belum memiliki riwayat kehadiran",
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
                        Spacer(Modifier.height(24.dp))
                        Text(
                            text = "Catatan Kehadiran",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
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
                        showToast(context, "Gagal memuat riwayat")
                    }

                    else -> {
                        Spacer(Modifier.height(24.dp))
                        Text(
                            text = "Catatan Kehadiran",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
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
                        repeat(3) {
                            Shimmer(
                                height = 72.dp,
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
}
