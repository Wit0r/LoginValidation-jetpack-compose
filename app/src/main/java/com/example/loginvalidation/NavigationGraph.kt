package com.example.loginvalidation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val Route: String) {
    object Splash : Screen("Splash")
    object Login : Screen("Login")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Screen.Splash.Route
    ) {
        composable("Splash") {
            SplashScreen(navController)
        }
        composable("Login") {
            ScreenLogin()
        }
    }
}
