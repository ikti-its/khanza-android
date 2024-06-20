package dev.ikti.pegawai.presentation

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.ikti.core.domain.model.screen.Screen
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.pegawai.R

@Composable
fun ViewContent(
    role: String,
    navController: NavHostController
) {
    MainScaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.navigationBarsPadding(),
                title = {
                    Text(
                        text = "Pegawai",
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
                    .padding(20.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    when (role) {
                        "Pegawai" -> {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Card(
                                    modifier = Modifier.size(68.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardColors(
                                        containerColor = Color(0xFFACF2E7),
                                        contentColor = Color.Unspecified,
                                        disabledContainerColor = Color(0xFFE8E8E8),
                                        disabledContentColor = Color(0xFFE8E8E8),
                                    ),
                                    onClick = {
                                        navController.navigate(
                                            Screen.Pegawai.route.replace("{role}", role)
                                                .replace("{feature}", "Data")
                                        )
                                    }
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_pegawai_data),
                                            contentDescription = null,
                                            tint = Color.Unspecified
                                        )
                                    }
                                }
                                Spacer(Modifier.height(6.dp))
                                Text(
                                    text = "Data\nPegawai",
                                    color = Color(0xFF0C203C),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp,
                                        fontFamily = FontGilroy
                                    ),
                                    textAlign = TextAlign.Center,
                                    minLines = 2
                                )
                            }
                        }

                        "Admin" -> {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Card(
                                    modifier = Modifier.size(68.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardColors(
                                        containerColor = Color(0xFFACF2E7),
                                        contentColor = Color.Unspecified,
                                        disabledContainerColor = Color(0xFFE8E8E8),
                                        disabledContentColor = Color(0xFFE8E8E8),
                                    ),
                                    onClick = {
                                        navController.navigate(
                                            Screen.Pegawai.route.replace("{role}", role)
                                                .replace("{feature}", "Data")
                                        )
                                    }
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_pegawai_data),
                                            contentDescription = null,
                                            tint = Color.Unspecified
                                        )
                                    }
                                }
                                Spacer(Modifier.height(6.dp))
                                Text(
                                    text = "Data\nPegawai",
                                    color = Color(0xFF0C203C),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp,
                                        fontFamily = FontGilroy
                                    ),
                                    textAlign = TextAlign.Center,
                                    minLines = 2
                                )
                            }
                            Spacer(Modifier.width(24.dp))
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Card(
                                    modifier = Modifier.size(68.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardColors(
                                        containerColor = Color(0xFFACF2E7),
                                        contentColor = Color.Unspecified,
                                        disabledContainerColor = Color(0xFFE8E8E8),
                                        disabledContentColor = Color(0xFFE8E8E8),
                                    ),
                                    onClick = {
                                        navController.navigate(
                                            Screen.Pegawai.route.replace("{role}", role)
                                                .replace("{feature}", "Daftar")
                                        )
                                    }
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_pegawai_ketersediaan),
                                            contentDescription = null,
                                            tint = Color.Unspecified
                                        )
                                    }
                                }
                                Spacer(Modifier.height(6.dp))
                                Text(
                                    text = "Daftar\nPegawai",
                                    color = Color(0xFF0C203C),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp,
                                        fontFamily = FontGilroy
                                    ),
                                    textAlign = TextAlign.Center,
                                    minLines = 2
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
