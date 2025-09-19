package com.koroliana.nekinopoisk.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.koroliana.nekinopoisk.R
import com.koroliana.nekinopoisk.data.entity.Film
import com.koroliana.nekinopoisk.ui.theme.NeKinopoiskTheme
import com.koroliana.nekinopoisk.viewmodel.FilmListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmListFragment : Fragment() {
    private val viewModel: FilmListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                NeKinopoiskTheme {

                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                    Log.d("Layout", "films size = ${uiState.films.size}")

                    FilmListScreen(
                        state = uiState,
                        onRetry = {viewModel.loadData()},
                        onGenreSelected = { genreId -> viewModel.onGenreSelected(genreId) },
                        onFilmClick = { film ->
                            navigateToDetails(parentFragmentManager, film)
                        }
                    )
                }
            }
        }
    }
}

fun navigateToDetails(fragmentManager: FragmentManager, film: Film) {
    fragmentManager.beginTransaction()
        .replace(R.id.fragment_container, FilmDetailsFragment.newInstance(film))
        .addToBackStack(null)
        .commit()
}