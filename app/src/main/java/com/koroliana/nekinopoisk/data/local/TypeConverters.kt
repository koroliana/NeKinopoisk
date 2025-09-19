package com.koroliana.nekinopoisk.data.local

import androidx.room.TypeConverter
import com.koroliana.nekinopoisk.data.entity.GenreIdsList

class Converters {

    @TypeConverter
    fun fromGenreIdsList(value: GenreIdsList): String {
        return value.ids.joinToString(separator = "|", prefix = "|", postfix = "|")
    }

    @TypeConverter
    fun toGenreIdsList(data: String): GenreIdsList {
        return if (data.isBlank()) GenreIdsList(emptyList())
        else GenreIdsList(
            data.split("|")
                .filter { it.isNotBlank() }
                .map { it.toInt() }
        )
    }
}