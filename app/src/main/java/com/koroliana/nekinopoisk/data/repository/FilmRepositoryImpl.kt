package com.koroliana.nekinopoisk.data.repository

import android.util.Log
import com.koroliana.nekinopoisk.data.entity.Film
import com.koroliana.nekinopoisk.data.entity.Genre
import com.koroliana.nekinopoisk.data.local.dao.FilmDao
import com.koroliana.nekinopoisk.data.local.dao.GenreDao
import com.koroliana.nekinopoisk.data.remote.mapper.toEntity
import com.koroliana.nekinopoisk.di.FilmApi

class FilmRepositoryImpl(
    private val filmDao: FilmDao,
    private val genreDao: GenreDao,
    private val api: FilmApi
):FilmRepository {
    override suspend fun getAllFilms(): List<Film> {
        return filmDao.getAllFilms()
    }

    override suspend fun getFilmsByGenreId(genreId: Int): List<Film> {
        return filmDao.getFilmsByGenreId(genreId)
    }

    override suspend fun insertFilms(films: List<Film>) {
        return filmDao.insertFilms(films)
    }

    override suspend fun refreshFilmsFromRemote() {
        val response = api.getAllFilms()
        val filmsDto = response.films

        val uniqueGenreNames = filmsDto
            .flatMap { it.genres }
            .toSet()

        val uniqueGenres = uniqueGenreNames
            .mapIndexed { index, name -> Genre(id = index, name = name) }

        genreDao.insertGenres(uniqueGenres)
        Log.i("FilmRepositoryImpl", "Genres added to DB:${uniqueGenres.size}" )

        val genreNameToId = uniqueGenres
            .associateBy({ it.name }, { it.id })

        val films = filmsDto.map { filmDto ->
            val genreIds = filmDto.genres.mapNotNull { genreNameToId[it] }
            filmDto.toEntity(genreIds = genreIds)
        }

        filmDao.insertFilms(films)
        Log.i("FilmRepositoryImpl", "Films added to DB:${films.size}" )
    }

}