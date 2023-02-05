package com.example.dayscheduler

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DayScheduler :Application(){

    override fun onCreate() {
        super.onCreate()
    }
}

/**
 * notes after development:
 * if u map some elements in a view to another item, and it is in lazy column, view will be recomposed
 *
 * */