package com.klyxdevs.kmptp2024.data.di

import com.klyxdevs.kmptp2024.ui.homeScreen.viewmodel.HomeScreenViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val uiModule = module {
    factoryOf(::HomeScreenViewModel)
}