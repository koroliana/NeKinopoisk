package com.koroliana.nekinopoisk.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.koroliana.nekinopoisk.data.local.entity.Film
import com.koroliana.nekinopoisk.ui.theme.NeKinopoiskTheme

class FilmDetailsFragment : Fragment() {

    companion object {
        private const val ARG_FILM = "arg_film"

        fun newInstance(film: Film): FilmDetailsFragment {
            return FilmDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_FILM, film)
                }
            }
        }
    }

    private val film: Film by lazy {
        requireArguments().getParcelable(ARG_FILM)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                NeKinopoiskTheme {
                    FilmDetailsScreen(
                        film = film,
                        onBackClick = {
                            navigateBack(parentFragmentManager)
                        }
                    )
                }
            }
        }
    }
}

fun navigateBack(fragmentManager: FragmentManager) {
    fragmentManager.popBackStack()
}