package banyuwangi.digital.moviecompose.ui.compontents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banyuwangi.digital.moviecompose.ui.theme.MovieComposeTheme
import banyuwangi.digital.moviecompose.ui.theme.gilroyRegular
import coil.compose.AsyncImage

@Composable
fun ActorItem(name: String, photoUrl: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .width(96.dp)
        .padding(end = 16.dp)) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/$photoUrl",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(80.dp)
                .clip(
                    CircleShape
                )
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = name,
            fontFamily = gilroyRegular,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.fillMaxWidth()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ActorPreview() {
    MovieComposeTheme {
        ActorItem("Ryan Reynolds", "")
    }
}