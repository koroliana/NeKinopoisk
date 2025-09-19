package com.koroliana.nekinopoisk.ui.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.koroliana.nekinopoisk.R
import com.koroliana.nekinopoisk.data.entity.Film
import com.koroliana.nekinopoisk.ui.components.ScreenHeader
import com.koroliana.nekinopoisk.ui.theme.MainBlue
import com.koroliana.nekinopoisk.viewmodel.FilmDetailsUiState

@SuppressLint("DefaultLocale")
@Composable
fun FilmDetailsScreen(
    state: FilmDetailsUiState,
    film: Film,
    onBackClick: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val darkIcons = false

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = MainBlue,
            darkIcons = darkIcons
        )
    }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val posterWidth = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        screenWidth * 0.25f
    } else {
        screenWidth * 0.44f
    }

    Scaffold(
        topBar = { ScreenHeader(
            title = film.name,
            showBackButton = true,
            onBackClick = { onBackClick() }
        ) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .fillMaxSize()
        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(film.imageUrl)
                    .error(R.drawable.placeholder)
                    .fallback(R.drawable.placeholder)
                    .crossfade(true)
                    .build()
            )

            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(posterWidth)
                    .aspectRatio(132f / 201f)
                    .clip(RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = film.localizedName,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            val genresText = state.genreNames.joinToString(", ")
            val subtitle = if (genresText.isNotEmpty()) {
                "$genresText, ${film.year}"
            } else {
                "${film.year}"
            }
            Text(
               text = subtitle,
               style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.Bottom
            ){
                if (film.rating != 0.0) {
                    Text(
                        text = String.format("%.1f", film.rating),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MainBlue
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                }

                Text(
                    text = "КиноПоиск",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MainBlue
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = film.description,
                style = MaterialTheme.typography.labelSmall
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}