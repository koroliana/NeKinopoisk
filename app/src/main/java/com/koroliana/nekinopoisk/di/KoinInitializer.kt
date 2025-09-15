package com.koroliana.nekinopoisk.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinInitializer(
    private val context: Context,
) {
    fun init() {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(

            )
        }
    }
}