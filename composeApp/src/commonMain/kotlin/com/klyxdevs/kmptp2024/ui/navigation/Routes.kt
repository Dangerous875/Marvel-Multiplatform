package com.klyxdevs.kmptp2024.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object HomeScreenRoute : Routes()
    @Serializable
    data object MainScreenRoute : Routes()
    @Serializable
    data class DetailScreenRoute(val id : Long) : Routes()
}