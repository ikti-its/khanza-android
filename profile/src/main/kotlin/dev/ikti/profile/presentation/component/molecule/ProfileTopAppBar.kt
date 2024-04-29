package dev.ikti.profile.presentation.component.molecule

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.profile.presentation.component.atom.ProfileTopButton
import dev.ikti.profile.presentation.component.atom.ProfileTopTitle

@Composable
fun ProfileTopAppBar(
    title: String,
    navigateBack: () -> Unit = {}
) {
    TopAppBar(
        title = {
            ProfileTopTitle(title)
        },
        navigationIcon = {
            ProfileTopButton { navigateBack() }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF0A2D27),
            navigationIconContentColor = Color(0xFFFFFFFF),
            titleContentColor = Color(0xFFFFFFFF)
        )
    )
}

@Preview
@Composable
fun ProfileTopAppBarPreview() {
    KhanzaTheme {
        MainScaffold(topBar = { ProfileTopAppBar("Profil") }) {}
    }
}
