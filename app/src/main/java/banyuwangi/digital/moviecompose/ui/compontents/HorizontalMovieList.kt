package banyuwangi.digital.moviecompose.ui.compontents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.moviecompose.ui.theme.gilroySemiBold


@Composable
fun MovieList(
    title: String,
    movies: List<MovieDomain>,
    modifier: Modifier = Modifier,
    navigateToMovieDetail: (Int) -> Unit
) {
    Spacer(modifier = modifier.height(16.dp))
    Text(text = title, fontSize = 16.sp, fontFamily = gilroySemiBold, color = Color.White)
    Spacer(modifier = modifier.height(16.dp))
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(movies, key = { it.id }) { movie ->
            RecommendationItem(
                title = movie.title,
                posterUrl = movie.posterPath,
                rating = movie.voteAverage,
                modifier = modifier.clickable {
                    navigateToMovieDetail(movie.id)
                }
            )
        }
    }
}