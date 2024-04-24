package dev.ikti.khanza.presentation.component.molecule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.R
import dev.ikti.khanza.presentation.component.atom.HomeFeatureLabel
import dev.ikti.khanza.presentation.navigation.model.BottomScreen
import dev.ikti.khanza.presentation.navigation.model.CScreen

@Composable
fun HomeFeatureGrid(
    modifier: Modifier,
    token: String,
    navController: NavHostController
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column {
            Row {
                HomeFeatureLabel()
            }
            Spacer(modifier = modifier.height(12.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeFeatureItem(
                    label = "Akun",
                    active = true,
                    color = Color(0xFFE9F6FF),
                    icon = R.drawable.ic_feature_c_akun,
                    iconColor = Color(0xFF007AFF),
                    onClick = {
                        navController.navigate(
                            CScreen.Akun.route.replace(
                                "{token}",
                                token
                            )
                        )
                    }
                )
                HomeFeatureItem(
                    label = "Kehadiran",
                    active = true,
                    color = Color(0xFFE9F6FF),
                    icon = R.drawable.ic_feature_c_kehadiran,
                    iconColor = Color(0xFF007AFF),
                    onClick = {
                        navController.navigate(
                            CScreen.Kehadiran.route.replace(
                                "{token}",
                                token
                            )
                        )
                    }
                )
                HomeFeatureItem(
                    label = "Pegawai",
                    active = true,
                    color = Color(0xFFE9F6FF),
                    icon = R.drawable.ic_feature_c_kepegawaian,
                    iconColor = Color(0xFF007AFF),
                    onClick = {
                        navController.navigate(
                            CScreen.Pegawai.route.replace(
                                "{token}",
                                token
                            )
                        )
                    }
                )
                HomeFeatureItem(label = "Modul A")
            }
            Spacer(modifier = modifier.height(18.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeFeatureItem(label = "Modul B")
                HomeFeatureItem(label = "Modul D")
                HomeFeatureItem(label = "Modul E")
                HomeFeatureItem(
                    label = "Lainnya",
                    icon = R.drawable.ic_feature_other,
                    onClick = { navController.navigate(BottomScreen.Home.route) }
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeFeatureGridPreview() {
    KhanzaTheme {
        HomeFeatureGrid(
            modifier = Modifier,
            token = "",
            navController = rememberNavController()
        )
    }
}