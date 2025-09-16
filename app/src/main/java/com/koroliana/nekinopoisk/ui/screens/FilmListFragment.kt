package com.koroliana.nekinopoisk.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.koroliana.nekinopoisk.R
import com.koroliana.nekinopoisk.ui.components.FilmItem
import com.koroliana.nekinopoisk.ui.components.ScreenHeader
import com.koroliana.nekinopoisk.ui.theme.NeKinopoiskTheme

class FilmListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                NeKinopoiskTheme {
                    Scaffold(
                        topBar = {
                            ScreenHeader("Подробности фильма")
                        }
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            FilmItem(name = "Фильм 1") {
                                navigateToDetails(parentFragmentManager)
                            }
                        }
                    }
                }
            }
        }
    }
}

fun navigateToDetails(fragmentManager: FragmentManager) {
    fragmentManager.beginTransaction()
        .replace(R.id.fragment_container, FilmDetailsFragment())
        .addToBackStack(null)
        .commit()
}