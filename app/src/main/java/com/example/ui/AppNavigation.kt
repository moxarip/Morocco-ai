package com.example.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "start",
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) }
    ) {
        composable("start") {
            StartScreen(
                onNavigateToLogin = { navController.navigate("login") }
            )
        }
        composable("login") {
            LoginScreen(
                onLoginSuccess = { isAdmin ->
                    navController.navigate("dashboard/$isAdmin") {
                        popUpTo("start") { inclusive = true }
                    }
                }
            )
        }
        composable("dashboard/{isAdmin}") { backStackEntry ->
            val isAdmin = backStackEntry.arguments?.getString("isAdmin")?.toBoolean() ?: false
            DashboardScreen(
                isAdmin = isAdmin,
                onNavigateToAdmin = { navController.navigate("admin") },
                onNavigateToCreationFlow = { prompt ->
                    navController.navigate("creationFlow/$prompt")
                }
            )
        }
        composable("admin") {
            AdminPanelScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("creationFlow/{prompt}") { backStackEntry ->
            val prompt = backStackEntry.arguments?.getString("prompt") ?: ""
            CreationFlowScreen(
                prompt = prompt,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
