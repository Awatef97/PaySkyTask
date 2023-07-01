package com.example.payskytask.core.application

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
@HiltAndroidApp
class PaySkyTaskApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        instance = this
    }
    companion object {
        lateinit var instance: PaySkyTaskApplication
            private set
    }
}