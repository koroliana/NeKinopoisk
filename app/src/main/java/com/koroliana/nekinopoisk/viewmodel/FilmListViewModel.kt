package com.koroliana.nekinopoisk.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koroliana.nekinopoisk.data.entity.Film
import com.koroliana.nekinopoisk.data.entity.Genre
import com.koroliana.nekinopoisk.data.repository.FilmRepository
import com.koroliana.nekinopoisk.data.repository.GenreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FilmListViewModel(
    private val filmRepository: FilmRepository,
    private val genreRepository: GenreRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(FilmListUiState())
    val uiState: StateFlow<FilmListUiState> = _uiState.asStateFlow()

    init {
        Log.i("FilmListViewModel", "ViewModel initialized")
        loadData()
    }

    fun loadData() {
        Log.i("FilmListViewModel", "loadData() started" )
        viewModelScope.launch {
            Log.i("FilmListViewModel", "Download from network" )
            try {
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        hasError = false
                    ) }

                filmRepository.refreshFilmsFromRemote()

                val genres = genreRepository.getAllGenres()
                val films = filmRepository.getAllFilms()

                _uiState.update {
                    it.copy(
                        genres = genres,
                        films = films,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                Log.e("FilmListViewModel", "Error with Download from network, message:${e.message}" )
                _uiState.update { it.copy(hasError = true, isLoading = false) }
            }
        }
    }

    fun onGenreSelected(genreId: Int?) {
        viewModelScope.launch {
            val films = if (genreId == null) {
                filmRepository.getAllFilms()
            } else {
                filmRepository.getFilmsByGenreId(genreId)
            }

            _uiState.update {
                it.copy(
                    films = films,
                    selectedGenreId = genreId
                )
            }
        }
    }
}

data class FilmListUiState(
    val isLoading: Boolean = true,
    val genres: List<Genre> = emptyList(),
    val films: List<Film> = emptyList(),
    val selectedGenreId: Int? = null,
    val hasError: Boolean = false
)