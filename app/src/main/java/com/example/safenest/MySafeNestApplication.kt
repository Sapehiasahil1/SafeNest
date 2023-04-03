package com.example.safenest

import android.app.Application

class MySafeNestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SharedPref.init(this)
    }
}