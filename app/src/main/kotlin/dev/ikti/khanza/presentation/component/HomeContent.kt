package dev.ikti.khanza.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun HomeContent(
    modifier: Modifier,
    navController: NavHostController
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Khanza50)
        ) {
            Column(
                modifier = modifier
                    .padding(24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val selectedFeature by remember { mutableIntStateOf(0) }
                val sheetState = rememberModalBottomSheetState()
                val scope = rememberCoroutineScope()
                var showBottomSheet by remember { mutableStateOf(false) }

                HomeContentDropdown(
                    modifier = modifier,
                    type = selectedFeature,
                ) {

                }
                Spacer(modifier = modifier.height(24.dp))

                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet = false
                        },
                        sheetState = sheetState,
                        containerColor = Khanza50
                    ) {
                        Text("test")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false, device = Devices.PIXEL_7_PRO)
@Composable
fun HomeContentPreview() {
    KhanzaTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HomeContent(
                modifier = Modifier,
                navController = rememberNavController()
            )
        }
    }
}
