package dev.ikti.khanza.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontPlusJakartaSans
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.KhanzaHomeBrush
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.R

@Composable
fun HomeHero(
    modifier: Modifier,
    innerPadding: PaddingValues,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(brush = KhanzaHomeBrush),
    ) {
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.home_welcome),
                color = Khanza50,
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    fontFamily = FontPlusJakartaSans
                )
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = "SIMKES Khanza", // TODO: Replace with user's name from Room
                color = Khanza50,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    fontFamily = FontPlusJakartaSans
                )
            )
        }
    }
}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun HomeHeroPreview() {
    KhanzaTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeHero(
                modifier = Modifier.fillMaxWidth(),
                innerPadding = PaddingValues(24.dp)
            )
        }
    }
}
