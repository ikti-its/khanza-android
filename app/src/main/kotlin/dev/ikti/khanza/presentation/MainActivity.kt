package dev.ikti.khanza.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.navigation.model.ModuleScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            KhanzaTheme {
                Surface {
                    MainApp(startDestination = ModuleScreen.Splash.route)
                }
            }
        }
    }
}
