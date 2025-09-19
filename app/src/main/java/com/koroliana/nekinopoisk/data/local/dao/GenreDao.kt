package com.koroliana.nekinopoisk.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.koroliana.nekinopoisk.data.entity.Genre

@Dao
interface GenreDao {
    @Query("SELECT * FROM genres ORDER BY name COLLATE LOCALIZED ASC")
    suspend fun getAllGenres(): List<Genre>

    @Query("SELECT * FROM genres WHERE id IN (:ids) ORDER BY name COLLATE LOCALIZED ASC")
    suspend fun getGenresByIds(ids: List<Int>): List<Genre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<Genre>)
}