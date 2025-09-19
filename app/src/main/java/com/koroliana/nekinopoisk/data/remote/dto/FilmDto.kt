package com.koroliana.nekinopoisk.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmResponse(
    val films: List<FilmDto>
)

@Serializable
data class FilmDto(
    val id: Int,
    val name: String,
    @SerialName("localized_name")
    val localizedName: String,
    val year: Int,
    val rating: Double? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    val description: String? = null,
    val genres: List<String>
)

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)