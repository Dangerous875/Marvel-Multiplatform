package com.klyxdevs.kmptp2024

import android.app.Application
import com.klyxdevs.kmptp2024.data.di.initKoin

class KMP2024App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}