package com.koroliana.nekinopoisk

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.koroliana.nekinopoisk.di.KoinInitializer
import com.koroliana.nekinopoisk.ui.screens.FilmListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KoinInitializer(this).init()
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FilmListFragment())
                .commit()
        }
    }
}