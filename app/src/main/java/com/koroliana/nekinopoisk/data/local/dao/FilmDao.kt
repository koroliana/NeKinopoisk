package com.koroliana.nekinopoisk.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.koroliana.nekinopoisk.data.entity.Film

@Dao
interface FilmDao {
    @Query("SELECT * FROM films ORDER BY localizedName COLLATE LOCALIZED ASC")
    suspend fun getAllFilms(): List<Film>

    @Query("SELECT * FROM films " +
            "WHERE genreIds LIKE '%' || '|' || :genreId || '|' || '%'")
    suspend fun getFilmsByGenreId(genreId: Int): List<Film>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<Film>)
}