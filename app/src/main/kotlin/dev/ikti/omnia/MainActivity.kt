package dev.ikti.omnia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import dev.ikti.core.domain.model.screen.Screen
import dev.ikti.core.presentation.theme.OMNIATheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            OMNIATheme {
                Surface {
                    MainApp(Screen.Splash.route)
                }
            }
        }
    }
}
