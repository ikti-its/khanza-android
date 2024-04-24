package dev.ikti.khanza.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import dev.ikti.khanza.presentation.navigation.NavigationHost

@Composable
fun MainApp(
    token: String,
    startDestination: String
) {
    NavigationHost(
        navController = rememberNavController(),
        token = token,
        startDestination = startDestination
    )
}
