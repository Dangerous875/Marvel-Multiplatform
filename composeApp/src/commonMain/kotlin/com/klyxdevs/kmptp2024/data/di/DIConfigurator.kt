package com.klyxdevs.kmptp2024.data.di

import com.klyxdevs.kmptp2024.data.di.dataModule
import com.klyxdevs.kmptp2024.data.di.domainModule
import com.klyxdevs.kmptp2024.data.di.uiModule
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