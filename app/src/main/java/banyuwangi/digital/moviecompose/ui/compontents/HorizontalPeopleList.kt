package banyuwangi.digital.moviecompose.ui.compontents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banyuwangi.digital.core.domain.model.PeopleDomain
import banyuwangi.digital.moviecompose.ui.theme.gilroySemiBold

@Composable
fun HorizontalPeopleList(
    title: String,
    peoples: List<PeopleDomain>,
    navigateToPeopleDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(text = title, fontSize = 16.sp, fontFamily = gilroySemiBold, color = Color.White)
    Spacer(modifier = modifier.height(16.dp))
    LazyRow {
        items(peoples, key = { it.id }) { people ->
            ActorItem(
                name = people.name,
                photoUrl = people.profilePath,
                modifier = Modifier.clickable {
                    navigateToPeopleDetail(people.id)
                }
            )
        }
    }
}
