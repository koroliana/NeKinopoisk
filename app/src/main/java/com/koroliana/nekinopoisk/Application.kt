package com.koroliana.nekinopoisk

import android.app.Application
import com.koroliana.nekinopoisk.di.KoinInitializer

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        KoinInitializer(this).init()
    }
}