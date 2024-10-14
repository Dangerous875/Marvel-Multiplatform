package com.klyxdevs.kmptp2024.data.di

import com.klyxdevs.kmptp2024.Greeting
import com.klyxdevs.kmptp2024.ui.detailScreen.viewmodel.DetailScreenViewmodel
import com.klyxdevs.kmptp2024.ui.homeScreen.local.WallpaperLogos
import com.klyxdevs.kmptp2024.ui.homeScreen.viewmodel.HomeScreenViewModel
import com.klyxdevs.kmptp2024.ui.mainScreen.viewmodel.MainScreenViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val uiModule = module {
    factoryOf(::HomeScreenViewModel)
    factoryOf(::MainScreenViewModel)
    factoryOf(::DetailScreenViewmodel)
    factoryOf(::WallpaperLogos)
    factoryOf(::Greeting)
}