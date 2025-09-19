package com.koroliana.nekinopoisk.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.koroliana.nekinopoisk.data.entity.Film
import com.koroliana.nekinopoisk.data.entity.Genre
import com.koroliana.nekinopoisk.data.local.dao.FilmDao
import com.koroliana.nekinopoisk.data.local.dao.GenreDao

@Database(
    entities = [Film::class, Genre::class],
    version = 1,
    exportSchema = true
)

@TypeConverters(Converters::class)
abstract class FilmDatabase: RoomDatabase() {
    abstract fun filmDao(): FilmDao
    abstract fun genreDao(): GenreDao
}