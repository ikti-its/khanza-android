package dev.ikti.khanza.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.KhanzaDark
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun HomeContentDropdown(
    modifier: Modifier,
    type: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 18.dp, vertical = 10.dp)
            .fillMaxWidth()
            .height(44.dp)
            .clickable(onClick = { onClick() }),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "Pilih menu", style = MaterialTheme.typography.bodyLarge)
        IconButton(
            onClick = {},
            colors = IconButtonColors(
                containerColor = Color.Transparent,
                contentColor = KhanzaDark,
                disabledContentColor = Khanza50,
                disabledContainerColor = Color.Transparent
            )
        ) {
            Icon(
                Icons.Rounded.KeyboardArrowDown,
                contentDescription = "Pilih menu"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentDropdownPreview() {
    KhanzaTheme {
        HomeContentDropdown(
            modifier = Modifier,
            type = 0,
            onClick = {}
        )
    }
}
