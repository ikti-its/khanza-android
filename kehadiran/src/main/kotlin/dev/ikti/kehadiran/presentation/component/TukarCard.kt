package dev.ikti.kehadiran.presentation.component

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.kehadiran.data.model.TukarRequest
import dev.ikti.kehadiran.domain.model.DaftarJadwal

@Composable
fun TukarCard(
    daftar: DaftarJadwal,
    hari: Int,
    senderId: String,
    senderShift: String,
    onClick: (TukarRequest) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF6F6F6)
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = daftar.pegawai,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        fontFamily = FontGilroy
                    )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "${daftar.masuk.substring(0, 5)} - ${daftar.pulang.substring(0, 5)}",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        fontFamily = FontGilroy
                    )
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Button(
                    onClick = {
                        val request = TukarRequest(
                            sender = senderId,
                            recipient = daftar.pegawaiId,
                            hari = hari,
                            senderShift = senderShift,
                            recipientShift = daftar.shiftId,
                            status = "Menunggu"
                        )

                        onClick(request)
                    },
                    modifier = Modifier
                        .width(120.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0A2D27),
                        contentColor = Color(0xFFACF2E7)
                    )
                ) {
                    Text(
                        text = "Tukar",
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
