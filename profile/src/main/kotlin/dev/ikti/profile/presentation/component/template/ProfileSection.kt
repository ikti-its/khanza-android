package dev.ikti.profile.presentation.component.template

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.domain.model.screen.AkunScreen
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.core.util.UIState
import dev.ikti.profile.presentation.component.organism.ProfileDetail
import dev.ikti.profile.presentation.component.organism.ProfileDetailHeader
import dev.ikti.profile.presentation.component.organism.ProfileEdit
import dev.ikti.profile.presentation.component.organism.ProfileView
import dev.ikti.profile.presentation.component.organism.ProfileViewHeader

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    type: String = "view",
    token: String = "",
    stateProfile: UIState<Unit> = UIState.Empty,
    userInfo: UserInfo,
    navController: NavHostController = rememberNavController(),
    onLogout: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        AnimatedContent(targetState = type, label = "profile-header") { current ->
            when (current) {
                "view" -> {
                    ProfileViewHeader(
                        stateProfile = stateProfile,
                        foto = userInfo.foto,
                        nama = userInfo.nama,
                        email = userInfo.email,
                        navigateToDetail = {
                            navController.navigate(AkunScreen.Detail.route)
                        },
                        navigateToEdit = {
                            navController.navigate(AkunScreen.Edit.route)
                        }
                    )
                }

                "detail", "edit" -> {
                    ProfileDetailHeader(url = userInfo.foto)
                }

                else -> {}
            }
        }
        AnimatedContent(targetState = type, label = "profile-content") { current ->
            when (current) {
                "view" -> {
                    ProfileView(
                        token = token,
                        navigateToDetail = {
                            navController.navigate(AkunScreen.Detail.route)
                        },
                        onLogout = { userToken ->
                            onLogout(userToken)
                        }
                    )
                }

                "detail" -> {
                    ProfileDetail(
                        email = userInfo.email,
                        role = userInfo.role,
                        alamat = userInfo.alamat
                    )
                }

                "edit" -> {
                    ProfileEdit(
                        email = userInfo.email,
                        role = userInfo.role,
                        alamat = userInfo.alamat,
                    )
                }

                else -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSectionLoadingViewPreview() {
    KhanzaTheme {
        ProfileSection(
            type = "view",
            userInfo = UserInfo(
                "",
                "IKTI",
                "ikti@fathoor.dev",
                "Developer",
                "https://api.fathoor.dev/v1/file/img/default.png",
                "Kampus ITS Surabaya",
                Float.NaN,
                Float.NaN
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSectionSuccessViewPreview() {
    KhanzaTheme {
        ProfileSection(
            type = "view",
            stateProfile = UIState.Success(Unit),
            userInfo = UserInfo(
                "",
                "IKTI",
                "ikti@fathoor.dev",
                "Developer",
                "https://api.fathoor.dev/v1/file/img/default.png",
                "Kampus ITS Surabaya",
                Float.NaN,
                Float.NaN
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSectionErrorViewPreview() {
    KhanzaTheme {
        ProfileSection(
            type = "view",
            stateProfile = UIState.Error(""),
            userInfo = UserInfo(
                "",
                "IKTI",
                "ikti@fathoor.dev",
                "Developer",
                "https://api.fathoor.dev/v1/file/img/default.png",
                "Kampus ITS Surabaya",
                Float.NaN,
                Float.NaN
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSectionLoadingDetailPreview() {
    KhanzaTheme {
        ProfileSection(
            type = "detail",
            userInfo = UserInfo(
                "",
                "IKTI",
                "ikti@fathoor.dev",
                "Developer",
                "https://api.fathoor.dev/v1/file/img/default.png",
                "Kampus ITS Surabaya",
                Float.NaN,
                Float.NaN
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSectionSuccessDetailPreview() {
    KhanzaTheme {
        ProfileSection(
            type = "detail",
            stateProfile = UIState.Success(Unit),
            userInfo = UserInfo(
                "",
                "IKTI",
                "ikti@fathoor.dev",
                "Developer",
                "https://api.fathoor.dev/v1/file/img/default.png",
                "Kampus ITS Surabaya",
                Float.NaN,
                Float.NaN
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSectionErrorDetailPreview() {
    KhanzaTheme {
        ProfileSection(
            type = "detail",
            stateProfile = UIState.Error(""),
            userInfo = UserInfo(
                "",
                "IKTI",
                "ikti@fathoor.dev",
                "Developer",
                "https://api.fathoor.dev/v1/file/img/default.png",
                "Kampus ITS Surabaya",
                Float.NaN,
                Float.NaN
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSectionLoadingEditPreview() {
    KhanzaTheme {
        ProfileSection(
            type = "edit",
            userInfo = UserInfo(
                "",
                "IKTI",
                "ikti@fathoor.dev",
                "Developer",
                "https://api.fathoor.dev/v1/file/img/default.png",
                "Kampus ITS Surabaya",
                Float.NaN,
                Float.NaN
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSectionSuccessEditPreview() {
    KhanzaTheme {
        ProfileSection(
            type = "edit",
            stateProfile = UIState.Success(Unit),
            userInfo = UserInfo(
                "",
                "IKTI",
                "ikti@fathoor.dev",
                "Developer",
                "https://api.fathoor.dev/v1/file/img/default.png",
                "Kampus ITS Surabaya",
                Float.NaN,
                Float.NaN
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSectionErrorEditPreview() {
    KhanzaTheme {
        ProfileSection(
            type = "edit",
            stateProfile = UIState.Error(""),
            userInfo = UserInfo(
                "",
                "IKTI",
                "ikti@fathoor.dev",
                "Developer",
                "https://api.fathoor.dev/v1/file/img/default.png",
                "Kampus ITS Surabaya",
                Float.NaN,
                Float.NaN
            )
        )
    }
}
