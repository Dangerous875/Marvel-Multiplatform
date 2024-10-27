package com.klyxdevs.kmptp2024.data.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

expect fun platformModule(): Module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            uiModule,
            domainModule,
            dataModule,
            platformModule()
        )
    }

fun initKoin() = initKoin {}
