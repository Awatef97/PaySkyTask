package com.example.payskytask.core.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
@HiltAndroidApp
class PaySkyTaskApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        lateinit var instance: PaySkyTaskApplication
            private set
    }
}