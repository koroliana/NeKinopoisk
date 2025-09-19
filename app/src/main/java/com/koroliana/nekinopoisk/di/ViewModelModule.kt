package com.koroliana.nekinopoisk.di

import com.koroliana.nekinopoisk.viewmodel.FilmDetailsViewModel
import com.koroliana.nekinopoisk.viewmodel.FilmListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FilmListViewModel(get(),get()) }
    viewModel { FilmDetailsViewModel(get()) }
}