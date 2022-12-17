package banyuwangi.digital.moviecompose.ui.screen.detail_movie

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment.Companion.Center
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.moviecompose.R
import banyuwangi.digital.moviecompose.ui.compontents.MovieList
import banyuwangi.digital.moviecompose.ui.theme.MovieComposeTheme
import banyuwangi.digital.moviecompose.ui.theme.gilroyRegular
import banyuwangi.digital.moviecompose.ui.theme.gilroySemiBold
import banyuwangi.digital.moviecompose.ui.theme.green500
import banyuwangi.digital.moviecompose.utils.Utils
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailMovieScreen(
    id: Int,
    viewModel: DetailMovieViewModel = koinViewModel()
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
            type = "movie",
            setWatchList = {
                viewModel.insertMovie()
            },
            isMovieWatchlist = state.isMovieWatchlist
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center
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

@Composable
fun DetailMovieContent(
    title: String,
    overview: String,
    genres: String,
    voteAverage: Double,
    backdropPath: String,
    releaseDate: String,
    runtime: Int,
    country: String,
    similarMovies: List<MovieDomain>,
    type: String,
    isMovieWatchlist:Boolean,
    setWatchList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 90.dp)
    ) {
        Box(modifier = modifier.height(300.dp)) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/$backdropPath",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(380.dp)
            )
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Black
                            )
                        )
                    )
                    .align(Alignment.BottomCenter)
                    .height(300.dp)
                    .fillMaxWidth()
            )
            Column(
                modifier = modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = title,
                    fontFamily = gilroySemiBold,
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = genres,
                    fontFamily = gilroyRegular,
                    color = Color.White,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row {
                    Text(
                        text = Utils.dateStringToYear(releaseDate),
                        fontFamily = gilroyRegular,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = country,
                        fontFamily = gilroyRegular,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (type=="movie") "$runtime minutes" else "$runtime episode",
                        fontFamily = gilroyRegular,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .wrapContentHeight(), verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = green500),
                shape = RoundedCornerShape(30),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Watch",
                    fontFamily = gilroySemiBold,
                    fontSize = 14.sp,
                    color = Color.Black,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_bookmark_border_24),
                contentDescription = null,
                tint = if (isMovieWatchlist) green500 else Color.White,
                modifier = Modifier.clickable {
                    setWatchList()
                }
            )
        }
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Description",
                fontFamily = gilroySemiBold,
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = overview, fontFamily = gilroyRegular, fontSize = 14.sp, color = Color.White)
            MovieList(
                title = if (type == "movie") "Similar Movie" else "Similar Tv",
                movies = similarMovies,
                navigateToMovieDetail = {})

        }
    }
}

@Preview
@Composable
fun DetailMovieScreenPreview() {
    MovieComposeTheme {
        DetailMovieContent(
            title = "Dune",
            overview = "A mythic and emotionally changed hero's journey",
            backdropPath = "",
            genres = "Sci-fi, Adventure",
            voteAverage = 8.5,
            releaseDate = "2022-12-20",
            runtime = 100,
            country = "USA",
            similarMovies = emptyList(),
            type = "movie",
            setWatchList = {},
            isMovieWatchlist = false
        )
    }
}