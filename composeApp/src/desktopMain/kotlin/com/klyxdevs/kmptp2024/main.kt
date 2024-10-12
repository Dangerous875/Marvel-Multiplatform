package com.klyxdevs.kmptp2024

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMPTP2024",
    ) {
        App()
    }
}