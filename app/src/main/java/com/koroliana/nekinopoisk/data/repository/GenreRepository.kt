package com.koroliana.nekinopoisk.data.repository

import com.koroliana.nekinopoisk.data.entity.Genre

interface GenreRepository {
    suspend fun getAllGenres(): List<Genre>
    suspend fun getGenresByIds(ids: List<Int>): List<Genre>
    suspend fun insertGenres(genres: List<Genre>)
}