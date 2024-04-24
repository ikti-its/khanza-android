package dev.ikti.khanza.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.presentation.navigation.model.BottomScreen
import dev.ikti.khanza.presentation.navigation.model.ModuleScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private var token: String = ""
    private var isNew: Boolean = false
    private var hasLoaded: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        observeUser()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen().apply {
                setKeepOnScreenCondition {
                    !hasLoaded
                }

                setOnExitAnimationListener { splash ->
                    splash.remove()
                    setContent {
                        KhanzaTheme {
                            Surface {
                                if (token != "" && !isNew) {
                                    MainApp(BottomScreen.Home.route)
                                } else {
                                    MainApp(ModuleScreen.Onboarding.route)
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (hasLoaded) {
                setContent {
                    KhanzaTheme {
                        Surface {
                            if (token != "" && !isNew) {
                                MainApp(BottomScreen.Home.route)
                            } else {
                                MainApp(ModuleScreen.Onboarding.route)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun observeUser() {
        mainViewModel.apply {
            userToken.observe(this@MainActivity) { userToken ->
                token = userToken
            }
            isNewUser.observe(this@MainActivity) { isNewUser ->
                isNew = isNewUser
            }
            isLoading.observe(this@MainActivity) { isLoading ->
                hasLoaded = !isLoading
            }

            observeIsNewUser(Unit)
        }
    }
}
