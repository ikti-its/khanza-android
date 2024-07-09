package dev.ikti.pegawai.presentation.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.pegawai.R
import dev.ikti.pegawai.domain.model.Ketersediaan

@Composable
fun KetersediaanCard(
    context: Context,
    ketersediaan: Ketersediaan
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isExpanded by remember { mutableStateOf(ketersediaan.isExpanded) }
    val distance = Math.round(ketersediaan.distance * 100.0) / 100.0

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF6F6F6)
        ),
        modifier = Modifier
            .animateContentSize(spring(Spring.DampingRatioNoBouncy, Spring.StiffnessLow))
            .fillMaxWidth()
            .clickable(interactionSource = interactionSource, indication = null) {
                isExpanded = !isExpanded
            }
            .border(width = 1.dp, color = Color(0xFFE5E7EB), shape = RoundedCornerShape(12.dp)),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF1F1F1))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(0.6f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SubcomposeAsyncImage(
                        model = ketersediaan.foto,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                    )
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text(
                            text = ketersediaan.nama,
                            style = TextStyle(
                                color = Color(0xFF0A2D27),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                fontFamily = FontGilroy
                            ),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "$distance km",
                        style = TextStyle(
                            color = Color(0xFF24A793),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            fontFamily = FontGilroy
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
            Spacer(
                Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFE5E7EB))
            )
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(8.dp))
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = fadeIn(),
                    exit = ExitTransition.None
                ) {
                    Column {
                        Text(
                            text = "Nama Lengkap",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                fontFamily = FontGilroy
                            )
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = ketersediaan.nama,
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                fontFamily = FontGilroy
                            ),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2
                        )
                        Spacer(Modifier.height(16.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                                Text(
                                    text = "NIP",
                                    style = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = ketersediaan.nip,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    text = "Telepon",
                                    style = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = "+62 ${ketersediaan.telepon.drop(1)}",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                            }
                            Spacer(Modifier.width(8.dp))
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Jabatan",
                                    style = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = ketersediaan.jabatan,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp,
                                        fontFamily = FontGilroy
                                    ),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 2
                                )
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    text = "Departemen",
                                    style = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        fontFamily = FontGilroy
                                    )
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = ketersediaan.departemen,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp,
                                        fontFamily = FontGilroy
                                    ),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 2
                                )
                            }
                        }
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = "Alamat",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                fontFamily = FontGilroy
                            )
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = ketersediaan.alamat,
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                fontFamily = FontGilroy
                            ),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3
                        )
                        Spacer(Modifier.height(16.dp))
                    }
                }
                AnimatedVisibility(
                    visible = !ketersediaan.available,
                    enter = EnterTransition.None,
                    exit = ExitTransition.None
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .width(8.dp)
                                .height(8.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .background(color = Color(0xFFDA4141))
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "Pegawai sedang cuti",
                            style = TextStyle(
                                color = Color(0xFFDA4141),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                fontFamily = FontGilroy
                            )
                        )
                    }
                    Spacer(Modifier.height(24.dp))
                }
                Button(
                    onClick = {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(
                                    String.format(
                                        "https://api.whatsapp.com/send?phone=%s&text=%s",
                                        "+62 ${ketersediaan.telepon.drop(1)}",
                                        "Kami butuh Anda di rumah sakit, apakah Anda bisa hadir?"
                                    )
                                )
                            )
                        )
                    },
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonColors(
                        containerColor = Color(0xFF0A2D27),
                        contentColor = Color(0xFFACF2E7),
                        disabledContainerColor = Color(0xFF8A8A8E),
                        disabledContentColor = Color(0xFFFFFFFF)
                    )
                ) {
                    Text(
                        text = "Hubungi",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = FontGilroy
                        )
                    )
                }
                Spacer(Modifier.height(8.dp))
                if (!isExpanded) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color(0xFF292D32)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowUp,
                        contentDescription = null,
                        tint = Color(0xFF292D32)
                    )
                }
            }
        }
    }
}
