package com.koroliana.nekinopoisk.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.koroliana.nekinopoisk.R
import com.koroliana.nekinopoisk.data.local.entity.Film
import com.koroliana.nekinopoisk.data.local.entity.GenreIdsList
import com.koroliana.nekinopoisk.ui.theme.NeKinopoiskTheme

class FilmListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                NeKinopoiskTheme {
                    FilmListScreen(
                        genres = listOf("Фэнтези","Драма","Криминал","Детектив"),
                        selectedGenre = null,
                        onGenreSelected = {navigateToDetails(parentFragmentManager)},
                        isLoading = false,
                        onFilmClick = {navigateToDetails(parentFragmentManager)},
                        films = testFilms
                    )
                }
            }
        }
    }
}

fun navigateToDetails(fragmentManager: FragmentManager) {
    fragmentManager.beginTransaction()
        .replace(R.id.fragment_container, FilmDetailsFragment())
        .addToBackStack(null)
        .commit()
}

val testFilms = listOf(
    Film(
        id = 326,
        name = "The Shawshank Redemption Redemption Redemption Redemption",
        localizedName = "Побег из Шоушенка - Побег Побег Побег Побег",
        year = 1994,
        rating = 9.196,
        imageUrl = "https://st.kp.yandex.net/images/film_iphone/iphone360_326.jpg",
        description = "Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника...",
        genreIds = GenreIdsList(listOf(0)) // драма
    ),
    Film(
        id = 435,
        name = "The Green Mile",
        localizedName = "Зеленая миля",
        year = 1999,
        rating = 9.064,
        imageUrl = "https://st.kp.yandex.net/images/film_iphone/iphone360_435.jpg",
        description = "Обвиненный в страшном преступлении, Джон Коффи оказывается в блоке смертников...",
        genreIds = GenreIdsList(listOf(1, 0, 2, 3)) // фэнтези, драма, криминал, детектив
    ),
    Film(
        id = 448,
        name = "Forrest Gump",
        localizedName = "Форрест Гамп",
        year = 1994,
        rating = 8.921,
        imageUrl = "https://st.kp.yandex.net/images/film_iphone/iphone360_448.jpg",
        description = "История необыкновенной жизни Форреста Гампа...",
        genreIds = GenreIdsList(listOf(0, 4)) // драма, мелодрама
    ),
    Film(
        id = 329,
        name = "Schindler's List",
        localizedName = "Список Шиндлера",
        year = 1993,
        rating = 8.817,
        imageUrl = "https://st.kp.yandex.net/images/film_iphone/iphone360_329.jpg",
        description = "История Оскара Шиндлера, спасшего 1200 евреев во время Холокоста.",
        genreIds = GenreIdsList(listOf(0, 5)) // драма, биография
    ),
    Film(
        id = 535341,
        name = "Intouchables",
        localizedName = "1+1",
        year = 2011,
        rating = 8.812,
        imageUrl = "https://st.kp.yandex.net/images/film_iphone/iphone360_535341.jpg",
        description = "Филипп нанимает Дрисса — человека, который меняет его жизнь.",
        genreIds = GenreIdsList(listOf(0, 6, 5)) // драма, комедия, биография
    ),
    Film(
        id = 447301,
        name = "Inception",
        localizedName = "Начало",
        year = 2010,
        rating = 8.767,
        imageUrl = "https://st.yandex.net/images/film/iphone360_447301.jpg",
        description = "Кобб крадет идеи из снов и готовится к самой сложной операции в своей жизни.",
        genreIds = GenreIdsList(listOf(7, 8, 9, 0, 3)) // фантастика, боевик, триллер, драма, детектив
    )
)

val testFilm = Film(
    id = 1,
    name = "The Green Mile",
    localizedName = "Зеленая миля",
    year = 1999,
    rating = 9.064,
    imageUrl = "https://st.kp.yandex.net/images/film_iphone/iphone360_435.jpg",
    description = "Джон Коффи оказывается в блоке смертников...",
    genreIds = GenreIdsList(listOf(1, 2, 3, 4)) // Привязка по id
)

/*
                    Scaffold(
                        topBar = {
                            ScreenHeader("Подробности фильма")
                        }
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {

                            Box (
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(48.dp)
                                )
                            }

                            FilmItem(name = "Фильм 1") {
                                navigateToDetails(parentFragmentManager)
                            }
                        }
                    }
                     */