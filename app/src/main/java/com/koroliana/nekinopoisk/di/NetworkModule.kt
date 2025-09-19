package com.koroliana.nekinopoisk.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.koroliana.nekinopoisk.data.remote.dto.FilmResponse
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.http.GET
import kotlinx.serialization.json.Json

object HttpRoutes {
    const val BASE_URL = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/"
    const val GET_FILMS = "films.json"
}

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single {
        Json { ignoreUnknownKeys = true }
    }

    single {
        Retrofit.Builder()
            .baseUrl(HttpRoutes.BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(FilmApi::class.java)
    }
}

interface FilmApi {
    @GET(HttpRoutes.GET_FILMS)
    suspend fun getAllFilms(): FilmResponse
}