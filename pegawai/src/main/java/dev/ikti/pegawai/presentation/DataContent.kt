package dev.ikti.pegawai.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.ikti.core.presentation.component.Shimmer
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.util.UIState
import dev.ikti.pegawai.data.model.PegawaiResponse

@Composable
fun DataContent(
    stateData: UIState<PegawaiResponse>,
    getData: () -> Unit,
    navController: NavHostController
) {
    val verticalScrollState = rememberScrollState()

    LaunchedEffect("") {
        getData()
    }

    MainScaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.navigationBarsPadding(),
                title = {
                    Text(
                        text = "Data Pegawai",
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
                            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
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
                    .verticalScroll(verticalScrollState)
            ) {
                when (stateData) {
                    is UIState.Success -> {
                        val user = stateData.data

                        Spacer(Modifier.height(24.dp))
                        Text(
                            text = user.nama,
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
                        Column {
                            Text(
                                text = "NIP",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = user.nip,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF535353),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "NIK",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = user.nik,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF535353),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Jenis Kelamin",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = user.jenisKelamin,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF535353),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Tempat Lahir",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = user.tempatLahir,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF535353),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Tanggal Lahir",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = user.tanggalLahir,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF535353),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Agama",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = user.agama,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF535353),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Pendidikan",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = user.pendidikan,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF535353),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Jabatan",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = user.jabatan,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF535353),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Departemen",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = user.departemen,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF535353),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Status",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = user.status,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF535353),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Jenis Pegawai",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = user.jenisPegawai,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF535353),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Telepon",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = user.telepon,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF535353),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Tanggal Masuk",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = user.tanggalMasuk,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF535353),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(24.dp))
                    }

                    is UIState.Error -> {
                        Spacer(Modifier.height(24.dp))
                        Text(
                            text = "Gagal memuat pegawai",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                                fontFamily = FontGilroy
                            )
                        )
                    }

                    else -> {
                        Spacer(Modifier.height(24.dp))
                        Shimmer(
                            height = 20.dp,
                            width = 370.dp,
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFF272727)
                        )
                        Spacer(Modifier.height(20.dp))
                        Spacer(
                            Modifier
                                .height(2.dp)
                                .fillMaxWidth()
                                .background(Color(0xFFF1F1F1))
                        )
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "NIP",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 50.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "NIK",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 50.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Jenis Kelamin",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 50.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Tempat Lahir",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 50.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Tanggal Lahir",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 50.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Agama",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 50.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Pendidikan",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 50.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Jabatan",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 50.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Departemen",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 50.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Status",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 50.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Jenis Pegawai",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 50.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Telepon",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 50.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column {
                            Text(
                                text = "Tanggal Masuk",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 50.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}
