package dev.ikti.profile.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.presentation.component.Shimmer
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.util.UIState
import dev.ikti.core.util.showToast
import dev.ikti.profile.presentation.component.molecule.ProfileDetailMap

@Composable
fun DetailContent(
    context: Context = LocalContext.current,
    stateProfile: UIState<UserInfo>,
    navController: NavHostController
) {
    MainScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Profil",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            fontFamily = FontGilroy
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
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
            Box(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .background(Color(0xFF0A2D27))
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .background(Color(0xFFACF2E7))
                    )
                }
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Spacer(Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(color = Color(0xFFF7F7F7), shape = CircleShape)
                        )
                        when (stateProfile) {
                            is UIState.Success -> {
                                SubcomposeAsyncImage(
                                    model = stateProfile.data.foto,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(72.dp)
                                        .clip(CircleShape)
                                )
                            }

                            else -> {
                                Shimmer(72.dp, 72.dp, CircleShape, Color(0xFF272727))
                            }
                        }

                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                when (stateProfile) {
                    is UIState.Success -> {
                        val profile = stateProfile.data

                        Column {
                            Text(
                                text = "Email",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = profile.email,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier
                                    .height(48.dp)
                                    .fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF272727),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Column {
                            Text(
                                text = "Role",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = profile.role,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy
                                ),
                                modifier = Modifier
                                    .height(48.dp)
                                    .fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF272727),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Column {
                            Text(
                                text = "Alamat Lengkap",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = profile.alamat,
                                onValueChange = {},
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    fontFamily = FontGilroy,
                                    lineHeight = 20.sp
                                ),
                                modifier = Modifier
                                    .heightIn(min = 48.dp)
                                    .fillMaxWidth(),
                                enabled = false,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    disabledTextColor = Color(0xFF272727),
                                    disabledContainerColor = Color(0xFFF0F0F0),
                                    disabledIndicatorColor = Color(0xFFF0F0F0)
                                )
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = "Denah Lokasi",
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                fontFamily = FontGilroy
                            )
                        )
                        Spacer(Modifier.height(8.dp))
                        ProfileDetailMap(
                            alamat = profile.alamat,
                            latitude = profile.alamatLat,
                            longitude = profile.alamatLon
                        )
                    }

                    is UIState.Error -> {
                        Column {
                            Text(
                                text = "Email",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 48.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Column {
                            Text(
                                text = "Role",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 48.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Column {
                            Text(
                                text = "Alamat Lengkap",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 48.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = "Denah Lokasi",
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                fontFamily = FontGilroy
                            )
                        )
                        Spacer(Modifier.height(8.dp))
                        Shimmer(
                            height = 200.dp,
                            width = 370.dp,
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFF272727)
                        )
                        showToast(context, "Gagal memuat profil")
                    }

                    else -> {
                        Column {
                            Text(
                                text = "Email",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 48.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Column {
                            Text(
                                text = "Role",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 48.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Column {
                            Text(
                                text = "Alamat Lengkap",
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Shimmer(
                                height = 48.dp,
                                width = 370.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF272727)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = "Denah Lokasi",
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                fontFamily = FontGilroy
                            )
                        )
                        Spacer(Modifier.height(8.dp))
                        Shimmer(
                            height = 200.dp,
                            width = 370.dp,
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFF272727)
                        )
                    }
                }
            }
        }
    }
}
