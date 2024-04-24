package dev.ikti.khanza.presentation.component.molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.presentation.component.atom.HomeDropdownIcon
import dev.ikti.khanza.presentation.component.atom.HomeDropdownLabel

@Composable
fun HomeDropdown(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                Color(0xFFE8E8E8),
                RoundedCornerShape(32.dp)
            )
            .height(44.dp)
            .clip(RoundedCornerShape(32.dp))
            .clickable { onClick() },
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                HomeDropdownLabel()
            }
            Column {
                HomeDropdownIcon()
            }
        }
    }
}

@Preview
@Composable
fun HomeDropdownPreview() {
    KhanzaTheme {
        HomeDropdown(
            modifier = Modifier,
            onClick = {}
        )
    }
}
