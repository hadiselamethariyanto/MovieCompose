package banyuwangi.digital.moviecompose.ui.compontents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import coil.compose.rememberAsyncImagePainter


@Composable
fun RecommendationItem(
    title: String,
    posterUrl: String,
    rating: Double,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(120.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/$posterUrl"),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(120.dp)
                .height(160.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontFamily = gilroyRegular,
            fontSize = 14.sp,
            color = Color.White,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(4.dp))
        Row {
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = purple100,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = String.format("%.1f", rating),
                fontFamily = gilroyMedium,
                fontSize = 14.sp,
                color = Color.White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationItemPreview() {
    MovieComposeTheme {
        RecommendationItem(
            title = "Black Panther: Wakanda Forever",
            posterUrl = " https://lumiere-a.akamaihd.net/v1/images/sumbrk_payoff_1sht_eng_4d993829.jpeg",
            rating = 9.3
        )
    }
}