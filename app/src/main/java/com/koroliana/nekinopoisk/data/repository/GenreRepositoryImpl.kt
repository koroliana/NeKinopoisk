package com.koroliana.nekinopoisk.data.repository

import com.koroliana.nekinopoisk.data.entity.Genre
import com.koroliana.nekinopoisk.data.local.dao.GenreDao

class GenreRepositoryImpl(private val genreDao: GenreDao):GenreRepository {
    override suspend fun getAllGenres(): List<Genre> {
        return genreDao.getAllGenres()
    }

    override suspend fun getGenresByIds(ids: List<Int>): List<Genre> {
        return genreDao.getGenresByIds(ids)
    }

    override suspend fun insertGenres(genres: List<Genre>) {
        return genreDao.insertGenres(genres)
    }
}