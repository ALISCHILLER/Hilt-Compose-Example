package com.msa.hilt_compose_example

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationExample:Application() {

    override fun onCreate() {
        super.onCreate()
    }
}