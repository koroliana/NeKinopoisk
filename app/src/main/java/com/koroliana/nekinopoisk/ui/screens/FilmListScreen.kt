package com.koroliana.nekinopoisk.ui.screens

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.koroliana.nekinopoisk.data.entity.Film
import com.koroliana.nekinopoisk.ui.components.FilmCard
import com.koroliana.nekinopoisk.ui.components.ScreenHeader
import com.koroliana.nekinopoisk.ui.theme.HighlightYellow
import com.koroliana.nekinopoisk.ui.theme.MainBlue
import com.koroliana.nekinopoisk.viewmodel.FilmListUiState


@Composable
fun FilmListScreen(
    state: FilmListUiState,
    onRetry:() -> Unit,
    onGenreSelected: (Int?) -> Unit,
    onFilmClick: (Film) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val darkIcons = false

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = MainBlue,
            darkIcons = darkIcons
        )
    }

     val isLoading = state.isLoading
     val genres = state.genres
     val selectedGenre = state.selectedGenreId
     val films = state.films

    val columns = if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 2

    Scaffold(
        topBar = { ScreenHeader("Фильмы") }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (isLoading) {
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
            } else if (state.hasError) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(Color.Black, shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Ошибка подключения",
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "ПОВТОРИТЬ",
                            color = HighlightYellow,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.clickable {
                                onRetry()
                            }
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 4.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    item {
                        Text(
                            text = "Жанры",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .height(40.dp)
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                        ) {
                            for (genre in genres) {
                                val isSelected = genre.id == selectedGenre
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                        .background(
                                            color = if (isSelected) HighlightYellow else Color.Transparent,
                                        )
                                        .clickable {
                                            onGenreSelected(
                                                if (isSelected) null else genre.id
                                            )
                                        }
                                        .padding(horizontal = 12.dp, vertical = 8.dp)
                                ) {
                                    Text(
                                        text = genre.name.replaceFirstChar { it.uppercaseChar() },
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item {
                        Text(
                            text = "Фильмы",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .height(40.dp),
                            color = MainBlue
                        )
                    }

                    Log.d("Layout", "films size = ${films.size}")


                    items(films.chunked(columns)) { rowFilms ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            rowFilms.forEach { film ->
                                Box(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    FilmCard(film = film, onClick = { onFilmClick(film) })
                                }
                            }

                            repeat(columns - rowFilms.size) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}