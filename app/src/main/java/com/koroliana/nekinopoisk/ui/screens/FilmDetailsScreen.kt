package com.koroliana.nekinopoisk.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.koroliana.nekinopoisk.R
import com.koroliana.nekinopoisk.data.local.entity.Film
import com.koroliana.nekinopoisk.ui.components.ScreenHeader
import com.koroliana.nekinopoisk.ui.theme.MainBlue

@Composable
fun FilmDetailsScreen(
    film: Film,
    onBackClick: () -> Unit
) {

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

            //todo Это временная заглушка
            Text(
                      text = "Кукушка, ${film.year}",
                       style = MaterialTheme.typography.bodyMedium
                  )

            // val genresText = film.genreIds.joinToString(", ") //todo здесь хранятся ids и они должны по id быть сопоставлены с жанрами видимо, а может нам просто сразу в фильме хранить названия жанров?
           // Text(
          //      text = "$genresText, ${film.year}",
         //       style = MaterialTheme.typography.bodyMedium
          //  )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.Bottom
            ){
                Text(
                    text = "${film.rating}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MainBlue
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "КиноПоиск",
                    style = MaterialTheme.typography.bodyMedium,
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