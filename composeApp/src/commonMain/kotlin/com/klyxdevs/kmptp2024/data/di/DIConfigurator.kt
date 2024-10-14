package com.klyxdevs.kmptp2024.data.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            uiModule,
            domainModule,
            dataModule
        )
    }
}