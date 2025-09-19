package com.koroliana.nekinopoisk.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
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