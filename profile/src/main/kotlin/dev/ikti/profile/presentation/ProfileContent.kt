package dev.ikti.profile.presentation

import android.location.Address
import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.domain.model.screen.AkunScreen
import dev.ikti.core.domain.model.screen.ModuleScreen
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.util.UIState
import dev.ikti.profile.data.model.ProfileRequest
import dev.ikti.profile.presentation.component.atom.ProfileTopHero
import dev.ikti.profile.presentation.component.molecule.ProfileTopAppBar
import dev.ikti.profile.presentation.component.template.ProfileSection

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    type: String = "view",
    token: String = "",
    stateProfile: UIState<Unit> = UIState.Empty,
    stateLogout: UIState<Unit> = UIState.Empty,
    stateUpload: UIState<String> = UIState.Empty,
    stateLocation: UIState<Address> = UIState.Empty,
    userInfo: UserInfo,
    navController: NavHostController = rememberNavController(),
    onLogout: (String) -> Unit = {},
    onSave: (ProfileRequest) -> Unit = {},
    onUpload: (Uri) -> Unit = {},
    onMarkerSearch: (Double, Double) -> Unit = { _, _ -> },
    intentToMap: (String) -> Unit
) {
    MainScaffold(
        modifier = modifier.navigationBarsPadding(),
        topBar = {
            AnimatedContent(
                targetState = type,
                transitionSpec = {
                    EnterTransition.None.togetherWith(fadeOut())
                },
                label = "topBar"
            ) { current ->
                ProfileTopAppBar(
                    title =
                    when (current) {
                        "view" -> AkunScreen.Halaman.title
                        "detail" -> AkunScreen.Detail.title
                        "edit" -> AkunScreen.Edit.title
                        else -> "Profil"
                    }
                ) { navController.popBackStack() }
            }
        },
        background = Color(0xFFF7F7F7)
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            ProfileTopHero()
            ProfileSection(
                type = type,
                token = token,
                stateProfile = stateProfile,
                stateUpload = stateUpload,
                stateLocation = stateLocation,
                userInfo = userInfo,
                navController = navController,
                onLogout = { userToken ->
                    onLogout(userToken)
                },
                onSave = { user ->
                    onSave(user)
                },
                onUpload = { uri ->
                    onUpload(uri)
                },
                onMarkerSearch = { lat, lon ->
                    onMarkerSearch(lat, lon)
                },
                intentToMap = intentToMap
            )
        }
        when (stateLogout) {
            is UIState.Success -> {
                navController.navigate(ModuleScreen.Onboarding.route) {
                    popUpTo(0) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }

            UIState.Loading -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    LinearProgressIndicator(
                        modifier = modifier.fillMaxWidth(),
                        color = Color(0xFFACF2E7),
                        trackColor = Color(0xFF0A2D27),
                        strokeCap = StrokeCap.Round
                    )
                }
            }

            is UIState.Error, UIState.Empty -> {}
        }
    }
}
