package dev.ikti.khanza.presentation.component.atom

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeDropdownLabel() {
    Text(
        text = "Pilih menu",
        style = MaterialTheme.typography.bodyLarge
    )
}