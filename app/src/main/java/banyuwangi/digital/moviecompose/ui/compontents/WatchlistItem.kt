package banyuwangi.digital.moviecompose.ui.compontents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banyuwangi.digital.moviecompose.ui.theme.*
import banyuwangi.digital.moviecompose.utils.Utils
import coil.compose.AsyncImage

@Composable
fun WatchlistItem(
    title: String,
    voteAverage: Double,
    releaseDate: String,
    country: String,
    runtime: Int,
    genres: String,
    overview: String,
    posterPath: String,
    modifier: Modifier = Modifier
) {

    Row(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/$posterPath",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(210.dp)
                .width(160.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontFamily = gilroySemiBold,
                fontSize = 20.sp,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = null,
                    tint = purple100,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = String.format("%.1f", voteAverage),
                    fontFamily = gilroyMedium,
                    fontSize = 14.sp,
                    color = Color.White,
                )
            }
            Spacer(modifier = modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = Utils.dateStringToYear(releaseDate),
                    fontFamily = gilroyRegular,
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = country,
                    fontFamily = gilroyRegular,
                    color = Color.White,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(2f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = runtime.toString(),
                    fontFamily = gilroyRegular,
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = genres,
                fontFamily = gilroyRegular,
                color = Color.White,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = overview,
                fontFamily = gilroyLight,
                color = Color.White,
                fontSize = 14.sp,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun WatchlistItemPreview() {
    MovieComposeTheme {
        WatchlistItem(
            title = "The King's Man",
            voteAverage = 9.3,
            releaseDate = "2022",
            country = "USA",
            runtime = 130,
            genres = "Action, Adventure",
            overview = "One man must race again time to stop history's worst tyrants and criminal masterminds as they get together to plot a war that",
            posterPath = ""
        )
    }
}