package com.koroliana.nekinopoisk.data.remote.mapper

import com.koroliana.nekinopoisk.data.entity.Film
import com.koroliana.nekinopoisk.data.entity.GenreIdsList
import com.koroliana.nekinopoisk.data.remote.dto.FilmDto

fun FilmDto.toEntity(genreIds: List<Int>): Film {
    return Film(
        id = id,
        name = name,
        localizedName = localizedName,
        year = year,
        rating = rating,
        imageUrl = imageUrl,
        description = description,
        genreIds = GenreIdsList(genreIds)
    )
}