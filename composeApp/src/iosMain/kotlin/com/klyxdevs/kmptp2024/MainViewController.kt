package com.klyxdevs.kmptp2024

import androidx.compose.ui.window.ComposeUIViewController
import com.klyxdevs.kmptp2024.data.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = initKoin()) { App() }