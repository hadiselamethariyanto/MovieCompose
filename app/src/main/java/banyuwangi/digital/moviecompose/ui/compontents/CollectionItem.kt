package banyuwangi.digital.moviecompose.ui.compontents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banyuwangi.digital.moviecompose.ui.theme.MovieComposeTheme
import banyuwangi.digital.moviecompose.ui.theme.black50
import banyuwangi.digital.moviecompose.ui.theme.gilroySemiBold
import coil.compose.AsyncImage

@Composable
fun CollectionItem(title: String, photoUrl: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.width(270.dp)) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/$photoUrl",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .width(270.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Box(
            modifier = modifier
                .matchParentSize()
                .background(black50)
                .clip(RoundedCornerShape(12.dp))
        )

        Text(
            text = title,
            fontFamily = gilroySemiBold,
            fontSize = 16.sp,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 8.dp, end = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CollectionItemPreview() {
    MovieComposeTheme {
        CollectionItem("Marvel Universe", "")
    }
}