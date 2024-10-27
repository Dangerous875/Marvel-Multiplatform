package com.klyxdevs.kmptp2024

import android.app.Application
import com.klyxdevs.kmptp2024.data.di.dataModule
import com.klyxdevs.kmptp2024.data.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KMP2024App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}