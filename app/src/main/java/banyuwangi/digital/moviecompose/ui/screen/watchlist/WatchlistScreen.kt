package banyuwangi.digital.moviecompose.ui.screen.watchlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banyuwangi.digital.core.domain.model.DetailMovieDomain
import banyuwangi.digital.moviecompose.ui.compontents.WatchlistItem
import banyuwangi.digital.moviecompose.ui.theme.gilroyMedium
import banyuwangi.digital.moviecompose.ui.theme.gilroySemiBold
import banyuwangi.digital.moviecompose.ui.theme.green500
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import org.koin.androidx.compose.koinViewModel

@Composable
fun WatchlistScreen(viewModel: WatchlistViewModel = koinViewModel()) {
    val state = viewModel.state
    val watchlist = state.watchlist ?: arrayListOf()

    if (watchlist.isNotEmpty()) {
        WatchlistContent(watchlist = watchlist)
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                EmptyState()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Watchlist is empty",
                    fontFamily = gilroyMedium,
                    color = Color.White,
                    fontSize = 14.sp
                )

            }
        }
    }

}

@Composable
fun EmptyState() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://assets7.lottiefiles.com/packages/lf20_CTaizi.json"))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(180.dp)
    )
}

@Composable
fun WatchlistContent(watchlist: List<DetailMovieDomain>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 60.dp)) {
        Text(text = "Watch list", fontFamily = gilroySemiBold, fontSize = 24.sp, color = green500)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(watchlist, key = { it.id }) {
                WatchlistItem(
                    title = it.title,
                    voteAverage = it.voteAverage,
                    releaseDate = it.releaseDate,
                    country = it.country,
                    runtime = it.runtime,
                    genres = it.genres.joinToString(separator = ", ") { genre -> genre.name },
                    overview = it.overview,
                    posterPath = it.backdropPath
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WatchlistScreenPreview() {

}