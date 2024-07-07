package dev.ikti.kehadiran.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import dev.ikti.core.util.formatDateString
import dev.ikti.kehadiran.data.model.PresensiResponse

@Composable
fun RiwayatCard(riwayat: PresensiResponse) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF6F6F6)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color(0xFFE5E7EB), shape = RoundedCornerShape(12.dp)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (riwayat.keterangan.isNullOrEmpty()) "Belum Pulang" else riwayat.keterangan,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = FontGilroy
                )
            )
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = formatDateString(riwayat.tanggal),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        fontFamily = FontGilroy
                    )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "${riwayat.masuk.substring(0, 5)} - ${
                        riwayat.pulang?.substring(0, 5) ?: "..."
                    }",
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
