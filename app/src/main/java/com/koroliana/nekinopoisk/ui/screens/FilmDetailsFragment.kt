package com.koroliana.nekinopoisk.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.koroliana.nekinopoisk.data.entity.Film
import com.koroliana.nekinopoisk.ui.theme.NeKinopoiskTheme
import com.koroliana.nekinopoisk.viewmodel.FilmDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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

    val viewModel: FilmDetailsViewModel by viewModel()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                NeKinopoiskTheme {
                    LaunchedEffect(Unit) {
                        viewModel.loadGenres(film.genreIds.ids)
                    }

                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                    FilmDetailsScreen(
                        state = uiState,
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