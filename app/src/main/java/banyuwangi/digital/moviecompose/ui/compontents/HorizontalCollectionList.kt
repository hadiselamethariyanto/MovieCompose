package banyuwangi.digital.moviecompose.ui.compontents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banyuwangi.digital.core.domain.model.CollectionDomain
import banyuwangi.digital.moviecompose.utils.FakeData
import banyuwangi.digital.moviecompose.ui.theme.MovieComposeTheme
import banyuwangi.digital.moviecompose.ui.theme.gilroySemiBold

@Composable
fun HorizontalCollectionList(
    title: String,
    collections: List<CollectionDomain>,
    modifier: Modifier = Modifier
) {
    Text(text = title, fontSize = 16.sp, fontFamily = gilroySemiBold, color = Color.White)
    Spacer(modifier = modifier.height(16.dp))
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(collections, key = { it.id }) { collection ->
            CollectionItem(title = collection.name, photoUrl = collection.backdropPath)
        }
    }
}

@Preview
@Composable
fun HorizontalCollectionListPreview() {
    MovieComposeTheme {
        HorizontalCollectionList("Collections", FakeData.collections)
    }
}