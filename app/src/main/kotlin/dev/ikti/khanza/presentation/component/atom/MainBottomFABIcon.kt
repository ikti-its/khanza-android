package dev.ikti.khanza.presentation.component.atom

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.core.R

@Composable
fun MainBottomFABIcon(modifier: Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.ic_scan),
        contentDescription = null,
        modifier = modifier.size(35.dp)
    )
}

@Preview
@Composable
fun MainBottomFABIconPreview() {
    KhanzaTheme {
        MainBottomFABIcon(Modifier)
    }
}
