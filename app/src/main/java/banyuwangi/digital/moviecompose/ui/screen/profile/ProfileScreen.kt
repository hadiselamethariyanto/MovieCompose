package banyuwangi.digital.moviecompose.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banyuwangi.digital.moviecompose.ui.theme.MovieComposeTheme
import banyuwangi.digital.moviecompose.ui.theme.gilroyRegular
import banyuwangi.digital.moviecompose.ui.theme.gilroySemiBold
import banyuwangi.digital.moviecompose.ui.theme.secondary2
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(all = 16.dp)) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .background(secondary2)
                .fillMaxWidth()
                .height(120.dp)
                .padding(all = 16.dp)
        ) {
            AsyncImage(
                model = "https://dicoding-web-img.sgp1.cdn.digitaloceanspaces.com/small/avatar/dos:3af05e4fec9f097f1d6837b52368aa5420221206134258.png",
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.fillMaxHeight()) {
                Text(
                    text = "Muhammad Hadi Selamet Hariyanto",
                    fontFamily = gilroySemiBold,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "adybeldex@gmail.com",
                    fontFamily = gilroyRegular,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }

}

@Preview
@Composable
fun ProfileScreenPreview() {
    MovieComposeTheme {
        ProfileScreen()
    }
}