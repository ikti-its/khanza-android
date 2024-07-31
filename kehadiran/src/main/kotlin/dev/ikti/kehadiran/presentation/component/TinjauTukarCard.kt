package dev.ikti.kehadiran.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.kehadiran.domain.model.DaftarTukar

@Composable
fun TinjauTukarCard(
    tinjauan: DaftarTukar,
    showDialog: (Boolean, DaftarTukar) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF6F6F6)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color(0xFFE5E7EB), shape = RoundedCornerShape(12.dp)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Text(
                        text = "Nama",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontGilroy
                        )
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = tinjauan.nama,
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            fontFamily = FontGilroy
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
                Spacer(Modifier.width(8.dp))
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Hari",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontGilroy
                        )
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = when (tinjauan.hari) {
                            1 -> "Senin"
                            2 -> "Selasa"
                            3 -> "Rabu"
                            4 -> "Kamis"
                            5 -> "Jumat"
                            6 -> "Sabtu"
                            else -> "Minggu"
                        },
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            fontFamily = FontGilroy
                        )
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Button(
                        onClick = {
                            showDialog(false, tinjauan)
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
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(Modifier.width(8.dp))
                    Button(
                        onClick = {
                            showDialog(true, tinjauan)
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
            }
        }
    }
}
