package com.klyxdevs.kmptp2024.data.di

import com.klyxdevs.kmptp2024.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory(get()) }
}