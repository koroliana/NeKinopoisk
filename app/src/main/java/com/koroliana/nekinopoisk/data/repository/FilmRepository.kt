package com.koroliana.nekinopoisk.data.repository

import com.koroliana.nekinopoisk.data.entity.Film

interface FilmRepository {
    suspend fun getAllFilms(): List<Film>
    suspend fun getFilmsByGenreId(genreId: Int): List<Film>
    suspend fun insertFilms(films: List<Film>)
    suspend fun refreshFilmsFromRemote()
}