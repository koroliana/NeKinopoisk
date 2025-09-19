package com.koroliana.nekinopoisk.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koroliana.nekinopoisk.data.local.dao.GenreDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmDetailsViewModel(
    private val genreDao: GenreDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(FilmDetailsUiState())
    val uiState: StateFlow<FilmDetailsUiState> = _uiState.asStateFlow()

    fun loadGenres(genreIds: List<Int>) {
        viewModelScope.launch {
            val genres = genreDao.getGenresByIds(genreIds)
            _uiState.value = FilmDetailsUiState(
                genreNames = genres.map { it.name }
            )
        }
    }
}

data class FilmDetailsUiState(
    val genreNames: List<String> = emptyList()
)