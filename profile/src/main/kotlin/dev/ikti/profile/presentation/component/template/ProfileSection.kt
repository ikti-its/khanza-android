package dev.ikti.profile.presentation.component.template

import android.location.Address
import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.domain.model.screen.AkunScreen
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.util.UIState
import dev.ikti.profile.data.model.ProfileRequest
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
    stateUpload: UIState<String> = UIState.Empty,
    stateLocation: UIState<Address> = UIState.Empty,
    userInfo: UserInfo,
    navController: NavHostController = rememberNavController(),
    onLogout: (String) -> Unit = {},
    onSave: (ProfileRequest) -> Unit = {},
    onUpload: (Uri) -> Unit = {},
    onMarkerSearch: (Double, Double) -> Unit = { _, _ -> },
    intentToMap: (String) -> Unit = {}
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

                "detail" -> {
                    ProfileDetailHeader(stateUpload = stateUpload, url = userInfo.foto)
                }

                "edit" -> {
                    ProfileDetailHeader(stateUpload = stateUpload, url = userInfo.foto)
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
                    if (!userInfo.alamatLat.isNaN() && !userInfo.alamatLon.isNaN()) {
                        ProfileDetail(
                            email = userInfo.email,
                            role = userInfo.role,
                            alamat = userInfo.alamat,
                            alamatLat = userInfo.alamatLat,
                            alamatLon = userInfo.alamatLon,
                            intentToMap = intentToMap
                        )
                    } else {
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
                }

                "edit" -> {
                    if (!userInfo.alamatLat.isNaN() && !userInfo.alamatLon.isNaN()) {
                        ProfileEdit(
                            stateUpload = stateUpload,
                            stateLocation = stateLocation,
                            foto = userInfo.foto,
                            akun = userInfo.akun,
                            email = userInfo.email,
                            role = userInfo.role,
                            alamat = userInfo.alamat,
                            alamatLat = userInfo.alamatLat,
                            alamatLon = userInfo.alamatLon,
                            onSave = { user ->
                                onSave(user)
                                navController.navigateUp()
                            },
                            onUpload = { uri ->
                                onUpload(uri)
                            },
                            onMarkerSearch = { lat, lon ->
                                onMarkerSearch(lat, lon)
                            }
                        )
                    } else {
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
                }

                else -> {}
            }
        }
    }
}
