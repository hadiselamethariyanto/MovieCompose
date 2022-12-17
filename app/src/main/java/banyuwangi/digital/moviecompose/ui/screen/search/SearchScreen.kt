package banyuwangi.digital.moviecompose.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banyuwangi.digital.moviecompose.R
import banyuwangi.digital.moviecompose.ui.compontents.RecommendationItem
import banyuwangi.digital.moviecompose.ui.screen.watchlist.EmptyState
import banyuwangi.digital.moviecompose.ui.theme.gilroySemiBold
import banyuwangi.digital.moviecompose.ui.theme.green500
import banyuwangi.digital.moviecompose.ui.theme.secondary2
import banyuwangi.digital.moviecompose.ui.theme.secondary3
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    navigateToMovieDetail: (Int) -> Unit
) {

    val state = viewModel.state
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                viewModel.onEvent(SearchEvent.OnSearchQueryChanged(""))
            },
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = "",
                tint = secondary3
            )
        }
    }
    Column(modifier = Modifier.padding(all = 16.dp)) {
        Text(
            text = stringResource(id = R.string.menu_search),
            fontFamily = gilroySemiBold,
            fontSize = 24.sp,
            color = green500
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { newValue ->
                viewModel.onEvent(
                    SearchEvent.OnSearchQueryChanged(
                        newValue
                    )
                )
            },
            placeholder = { Text(text = "Type movie title", color = secondary3) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = secondary3
                )
            },
            trailingIcon = if (state.searchQuery.isNotBlank()) trailingIconView else null,
            colors = TextFieldDefaults.textFieldColors(textColor = Color.White),
            shape = RoundedCornerShape(36.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(secondary2, RoundedCornerShape(36.dp))
        )

        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (state.movies.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                EmptyState(title = "Result is empty")
            }
        } else if (state.movies.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 60.dp)
            ) {
                items(state.movies, key = { it.id }) {
                    RecommendationItem(
                        title = it.title,
                        posterUrl = it.posterPath,
                        rating = it.voteAverage,
                        modifier = Modifier.clickable {
                            navigateToMovieDetail(it.id)
                        }
                    )
                }
            }
        } else {
            Text(
                text = state.error ?: "",
                color = MaterialTheme.colors.error
            )
        }
    }

}


@Preview
@Composable
fun SearchScreenPreview() {

}