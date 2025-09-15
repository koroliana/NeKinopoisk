package com.koroliana.nekinopoisk.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.koroliana.nekinopoisk.ui.theme.NeKinopoiskTheme

class FilmDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                NeKinopoiskTheme {
                    Text("Подробности фильма")
                }
            }
        }
    }
}

fun navigateBack(fragmentManager: FragmentManager) {
    fragmentManager.popBackStack()
}