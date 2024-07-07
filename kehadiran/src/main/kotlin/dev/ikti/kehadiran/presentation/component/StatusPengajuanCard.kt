package dev.ikti.kehadiran.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.util.formatDateString
import dev.ikti.kehadiran.R
import dev.ikti.kehadiran.data.model.PengajuanResponse

@Composable
fun StatusPengajuanCard(
    ajuan: PengajuanResponse
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF6F6F6)
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    when (ajuan.status) {
                        "Diproses" -> {
                            Color(0xFFFEFCE8)
                        }

                        "Diterima" -> {
                            Color(0xFFF0FDFA)
                        }

                        else -> {
                            Color(0xFFFFF9F9)
                        }
                    },
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
                .border(
                    width = 1.dp,
                    color = when (ajuan.status) {
                        "Diproses" -> {
                            Color(0xFFC7BA58)
                        }

                        "Diterima" -> {
                            Color(0xFF26B29D)
                        }

                        else -> {
                            Color(0xFFE7B1B1)
                        }
                    },
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        when (ajuan.status) {
                            "Diproses" -> {
                                Color(0xFFFEF9C3)
                            }

                            "Diterima" -> {
                                Color(0xFFD6F9F3)
                            }

                            else -> {
                                Color(0xFFFFEBEB)
                            }
                        },
                        shape = RoundedCornerShape(24.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = when (ajuan.status) {
                            "Diproses" -> {
                                Color(0xFFC7BA58)
                            }

                            "Diterima" -> {
                                Color(0xFF26B29D)
                            }

                            else -> {
                                Color(0xFFE7B1B1)
                            }
                        },
                        shape = RoundedCornerShape(24.dp)
                    )
                    .clip(RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(
                            id = when (ajuan.status) {
                                "Diproses" -> {
                                    R.drawable.ic_status_diproses
                                }

                                "Diterima" -> {
                                    R.drawable.ic_status_diterima
                                }

                                else -> {
                                    R.drawable.ic_status_ditolak
                                }
                            }
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = if (ajuan.status == "Diproses") "Dalam Proses" else ajuan.status,
                        color = when (ajuan.status) {
                            "Diproses" -> {
                                Color(0xFFA46319)
                            }

                            "Diterima" -> {
                                Color(0xFF13594E)
                            }

                            else -> {
                                Color(0xFFDA4141)
                            }
                        },
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            fontFamily = FontGilroy
                        )
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFFE5E7EB),
                    shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Text(
                        text = "Tanggal Mulai",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontGilroy
                        )
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = formatDateString(ajuan.mulai),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            fontFamily = FontGilroy
                        )
                    )
                }
                Spacer(Modifier.width(8.dp))
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Tanggal Selesai",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontGilroy
                        )
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = formatDateString(ajuan.selesai),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            fontFamily = FontGilroy
                        )
                    )
                }
            }
        }
    }
}
