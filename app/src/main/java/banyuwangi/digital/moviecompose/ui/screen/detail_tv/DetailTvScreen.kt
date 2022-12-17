package banyuwangi.digital.moviecompose.ui.screen.detail_tv

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import banyuwangi.digital.moviecompose.ui.screen.detail_movie.DetailMovieContent
import org.koin.androidx.compose.koinViewModel


@Composable
fun DetailTvScreen(
    id: Int,
    viewModel: DetailTvViewModel = koinViewModel()
) {
    val state = viewModel.state

    if (state.error == null) {
        val detailMovie = state.detailMovie
        val similarMovies = state.similarMovies
        val genres = detailMovie?.genres ?: arrayListOf()
        DetailMovieContent(
            title = detailMovie?.title ?: "",
            overview = detailMovie?.overview ?: "",
            backdropPath = detailMovie?.backdropPath ?: "",
            voteAverage = detailMovie?.voteAverage ?: 0.0,
            genres = genres.joinToString(separator = ", ") { it.name },
            releaseDate = detailMovie?.releaseDate ?: "",
            runtime = detailMovie?.runtime ?: 0,
            country = detailMovie?.country ?: "",
            similarMovies = similarMovies,
            type = "tv",
            setWatchList = {},
            isMovieWatchlist = false
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error
            )
        }
    }
}