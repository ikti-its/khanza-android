package dev.ikti.pegawai.presentation

import android.content.Context
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import dev.ikti.pegawai.domain.model.Ketersediaan
import dev.ikti.pegawai.presentation.component.KetersediaanCard
import kotlinx.coroutines.delay

@Composable
fun KetersediaanContent(
    context: Context = LocalContext.current,
    stateKetersediaan: UIState<List<Ketersediaan>>,
    onQuery: (String) -> Unit,
    navController: NavHostController
) {
    val lazyListState = rememberLazyListState()
    var query by remember { mutableStateOf("") }

    LaunchedEffect(query) {
        delay(1000L)
        onQuery(query)
    }

    MainScaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.navigationBarsPadding(),
                title = {
                    Text(
                        text = "Ketersediaan Pegawai",
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
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Row {
                    OutlinedTextField(
                        value = query,
                        onValueChange = { input ->
                            query = input
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            fontFamily = FontGilroy
                        ),
                        placeholder = {
                            Text(
                                text = "Cari",
                                style = TextStyle(
                                    color = Color(0xFF666666),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = null,
                                tint = Color(0xFF666666)
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF1F1F1),
                            focusedContainerColor = Color(0xFFF1F1F1),
                            unfocusedBorderColor = Color(0xFFE5E7EB),
                            focusedBorderColor = Color(0xFFE5E7EB)
                        )
                    )
                }
            }
            Spacer(
                Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFF1F1F1))
            )
            when (stateKetersediaan) {
                is UIState.Success -> {
                    Spacer(Modifier.height(16.dp))
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        state = lazyListState
                    ) {
                        items(items = stateKetersediaan.data) { ketersediaan ->
                            KetersediaanCard(context = context, ketersediaan = ketersediaan)
                            Spacer(Modifier.height(16.dp))
                        }
                    }
                }

                is UIState.Error -> {
                    showToast(context, "Gagal memuat ketersediaan pegawai")
                }

                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(24.dp))
                        Shimmer(
                            height = 175.dp,
                            width = 370.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF272727)
                        )
                        Spacer(Modifier.height(16.dp))
                        Shimmer(
                            height = 175.dp,
                            width = 370.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF272727)
                        )
                        Spacer(Modifier.height(16.dp))
                        Shimmer(
                            height = 175.dp,
                            width = 370.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF272727)
                        )
                    }
                }
            }
        }
    }
}
