package dev.ikti.khanza.presentation.component.molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontPlusJakartaSans
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.KhanzaHomeBrush
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.R

@Composable
fun HomeHeroCard(
    modifier: Modifier,
    height: Dp = 150.dp,
    token: String,
    onClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(brush = KhanzaHomeBrush, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(token) },
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = modifier
                .padding(vertical = 24.dp, horizontal = 24.dp),
            verticalArrangement = Arrangement.Top,
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
            Spacer(modifier = modifier.height(4.dp))
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
fun HomeHeroCardPreview() {
    KhanzaTheme {
        HomeHeroCard(Modifier, 150.dp, "") { _ -> }
    }
}