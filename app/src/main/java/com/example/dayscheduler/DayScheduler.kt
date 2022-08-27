package com.example.dayscheduler

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DayScheduler :Application(){

    override fun onCreate() {
        super.onCreate()
    }
}