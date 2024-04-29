package dev.ikti.profile.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.domain.model.screen.AkunScreen
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.util.UIState
import dev.ikti.profile.presentation.component.atom.ProfileTopHero
import dev.ikti.profile.presentation.component.molecule.ProfileTopAppBar
import dev.ikti.profile.presentation.component.template.ProfileSection

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    type: String = "view",
    stateProfile: UIState<Unit> = UIState.Empty,
    userInfo: UserInfo,
    navController: NavHostController = rememberNavController()
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
                stateProfile = stateProfile,
                userInfo = userInfo,
                navController = navController
            )
        }
    }
}
