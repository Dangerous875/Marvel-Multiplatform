package com.klyxdevs.kmptp2024.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.klyxdevs.kmptp2024.ui.Test.Test
import com.klyxdevs.kmptp2024.ui.core.navigation.Routes
import com.klyxdevs.kmptp2024.ui.detailScreen.DetailScreen
import com.klyxdevs.kmptp2024.ui.homeScreen.HomeScreen
import com.klyxdevs.kmptp2024.ui.mainScreen.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HomeScreenRoute) {
        composable<Routes.HomeScreenRoute> { HomeScreen(navController) }
        composable<Routes.MainScreenRoute> { MainScreen(navController) }
        composable<Routes.DetailScreenRoute> {
            val safeArgs = it.toRoute<Routes.DetailScreenRoute>()
            DetailScreen(navController, heroID = safeArgs.id)
        }
        composable<Routes.TestScreen> { Test() }
    }
}

