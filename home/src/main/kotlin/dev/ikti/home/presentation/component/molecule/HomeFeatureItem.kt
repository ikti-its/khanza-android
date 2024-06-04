package dev.ikti.home.presentation.component.molecule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.home.R
import dev.ikti.home.presentation.component.atom.HomeFeatureItemIcon
import dev.ikti.home.presentation.component.atom.HomeFeatureItemLabel

@Composable
fun HomeFeatureItem(
    modifier: Modifier = Modifier,
    label: String,
    active: Boolean = false,
    color: Color = Color(0xFFE8E8E8),
    icon: Int = R.drawable.ic_dev,
    iconColor: Color = Color(0xFF272727),
    onClick: () -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = modifier.size(68.dp),
            enabled = active,
            shape = RoundedCornerShape(12.dp),
            colors = CardColors(
                containerColor = color,
                contentColor = Color.Unspecified,
                disabledContainerColor = Color(0xFFE8E8E8),
                disabledContentColor = Color(0xFFE8E8E8),
            ),
            onClick = onClick
        ) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                HomeFeatureItemIcon(icon = icon, iconColor = iconColor)
            }
        }
        Spacer(modifier = modifier.height(6.dp))
        HomeFeatureItemLabel(label)
    }
}

@Preview
@Composable
fun HomeFeatureItemPreview() {
    KhanzaTheme {
        HomeFeatureItem(label = "DEV")
    }
}