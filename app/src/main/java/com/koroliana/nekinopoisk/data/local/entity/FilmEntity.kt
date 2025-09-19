package com.koroliana.nekinopoisk.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Entity(tableName = "films")
data class Film(
    @PrimaryKey
    val id: Int,
    val name: String,
    val localizedName: String,
    val year: Int,
    val rating: Double?,
    val imageUrl: String,
    val description: String,
    val genreIds: GenreIdsList
): Parcelable

@Parcelize
@Serializable
data class GenreIdsList(
    val ids: List<Int>
) : Parcelable

class Converters {

    @TypeConverter
    fun fromGenreIdsList(value: GenreIdsList): String {
        return value.ids.joinToString(separator = "|")
    }

    @TypeConverter
    fun toGenreIdsList(data: String): GenreIdsList {
        return if (data.isBlank()) GenreIdsList(emptyList())
        else GenreIdsList(data.split("|").map { it.toInt() })
    }
}