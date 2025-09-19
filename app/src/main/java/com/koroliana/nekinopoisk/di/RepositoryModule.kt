package com.koroliana.nekinopoisk.di

import com.koroliana.nekinopoisk.data.local.dao.FilmDao
import com.koroliana.nekinopoisk.data.local.dao.GenreDao
import com.koroliana.nekinopoisk.data.repository.FilmRepository
import com.koroliana.nekinopoisk.data.repository.FilmRepositoryImpl
import com.koroliana.nekinopoisk.data.repository.GenreRepository
import com.koroliana.nekinopoisk.data.repository.GenreRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    fun provideFilmRepository(
        filmDao: FilmDao,
        genreDao: GenreDao,
        api: FilmApi
    ): FilmRepository {
        return FilmRepositoryImpl(filmDao, genreDao, api)
    }

    fun provideGenreRepository(
        genreDao: GenreDao
    ): GenreRepository {
        return GenreRepositoryImpl(genreDao)
    }

    single{ provideFilmRepository(get(),get(), get()) }
    single{ provideGenreRepository(get()) }
}