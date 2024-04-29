package dev.ikti.profile.presentation.component.organism

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.core.util.UIState
import dev.ikti.profile.presentation.component.molecule.ProfileCard

@Composable
fun ProfileViewHeader(
    stateProfile: UIState<Unit> = UIState.Loading,
    foto: String = "https://api.fathoor.dev/v1/file/img/default.png",
    nama: String = "Pengguna",
    email: String = "user@fathoor.dev",
    navigateToDetail: () -> Unit = {},
    navigateToEdit: () -> Unit = {}
) {
    ProfileCard(
        stateProfile = stateProfile,
        foto = foto,
        nama = nama,
        email = email,
        navigateToDetail = { navigateToDetail() },
        navigateToEdit = { navigateToEdit() }
    )
}

@Preview
@Composable
fun ProfileViewHeaderLoadingPreview() {
    KhanzaTheme {
        ProfileViewHeader(
            nama = "IKTI",
            email = "ikti@fathoor.dev"
        )
    }
}

@Preview
@Composable
fun ProfileViewHeaderSuccessPreview() {
    KhanzaTheme {
        ProfileViewHeader(
            stateProfile = UIState.Success(Unit),
            nama = "IKTI",
            email = "ikti@fathoor.dev"
        )
    }
}

@Preview
@Composable
fun ProfileViewHeaderErrorPreview() {
    KhanzaTheme {
        ProfileViewHeader(
            stateProfile = UIState.Error(""),
            nama = "IKTI",
            email = "ikti@fathoor.dev"
        )
    }
}
