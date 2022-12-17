package banyuwangi.digital.moviecompose.ui.screen.detail_people

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.moviecompose.ui.compontents.MovieList
import banyuwangi.digital.moviecompose.ui.theme.gilroyRegular
import banyuwangi.digital.moviecompose.ui.theme.gilroySemiBold
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel


@Composable
fun DetailPeopleScreen(id: Int, viewModel: DetailPeopleViewModel = koinViewModel()) {
    val state = viewModel.state

    if (state.error == null) {
        val detail = state.detail
        val filmography = state.filmography

        DetailPeopleContent(
            name = detail?.name ?: "",
            birthday = detail?.birthday ?: "",
            placeOfBirth = detail?.placeOfBirth ?: "",
            profilePath = detail?.profilePath ?: "",
            biography = detail?.biography ?: "",
            filmography = filmography
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

@Composable
fun DetailPeopleContent(
    name: String,
    birthday: String,
    placeOfBirth: String,
    profilePath: String,
    biography: String,
    filmography: List<MovieDomain>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 60.dp)
    ) {
        Box(modifier = modifier.height(300.dp)) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/$profilePath",
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
                    text = name,
                    fontFamily = gilroySemiBold,
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = birthday,
                    fontFamily = gilroyRegular,
                    color = Color.White,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = placeOfBirth,
                    fontFamily = gilroyRegular,
                    color = Color.White,
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = biography,
                fontFamily = gilroyRegular,
                fontSize = 14.sp,
                color = Color.White
            )
            MovieList(
                title = "Filmography",
                movies = filmography,
                navigateToMovieDetail = {})
        }
    }
}