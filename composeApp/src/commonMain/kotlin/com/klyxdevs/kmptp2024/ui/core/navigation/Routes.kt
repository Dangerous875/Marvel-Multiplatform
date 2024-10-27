package com.klyxdevs.kmptp2024.ui.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object HomeScreenRoute : Routes()
    @Serializable
    data object MainScreenRoute : Routes()
    @Serializable
    data class DetailScreenRoute(val id : Long) : Routes()
    @Serializable
    data object TestScreen : Routes()
}