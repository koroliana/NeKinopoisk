package com.koroliana.nekinopoisk.di

import androidx.room.Room
import com.koroliana.nekinopoisk.data.local.FilmDatabase
import com.koroliana.nekinopoisk.data.local.dao.FilmDao
import com.koroliana.nekinopoisk.data.local.dao.GenreDao
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = FilmDatabase::class.java,
            name = "films.db"
        )
            .build()
    }
}

val daoModule = module {

    fun provideFilmDao(database: FilmDatabase): FilmDao {
        return database.filmDao()
    }

    fun provideGenreDao(database: FilmDatabase): GenreDao {
        return database.genreDao()
    }

    single { provideFilmDao(get()) }
    single { provideGenreDao(get()) }
}