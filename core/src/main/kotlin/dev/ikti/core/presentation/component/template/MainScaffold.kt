package dev.ikti.core.presentation.component.template

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    background: Color = Color(0xff000000),
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
    ) {
        Surface(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
            color = background
        ) {
            content()
        }
    }
}