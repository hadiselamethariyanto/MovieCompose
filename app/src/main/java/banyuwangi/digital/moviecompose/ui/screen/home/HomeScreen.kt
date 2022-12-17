package banyuwangi.digital.moviecompose.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import banyuwangi.digital.core.domain.model.CollectionDomain
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.core.domain.model.PeopleDomain
import banyuwangi.digital.moviecompose.ui.compontents.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    navigateToMovieDetail: (Int) -> Unit,
    navigateToTvDetail: (Int) -> Unit,
    navigateToPeopleDetail: (Int) -> Unit,
    navigateToCollectionDetail: (Int, String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(start = 16.dp, bottom = 55.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val state = viewModel.state
        if (state.error == null) {
            HomeContent(
                popularMovies = state.popularMovies,
                popularTvs = state.popularTv,
                collections = state.collections,
                popularPeople = state.popularPeople,
                navigateToMovieDetail = navigateToMovieDetail,
                navigateToTvDetail = navigateToTvDetail,
                navigateToPeopleDetail = navigateToPeopleDetail,
                navigateToCollectionDetail = navigateToCollectionDetail
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
}

@Composable
fun HomeContent(
    popularMovies: List<MovieDomain>,
    popularTvs: List<MovieDomain>,
    collections: List<CollectionDomain>,
    popularPeople: List<PeopleDomain>,
    modifier: Modifier = Modifier,
    navigateToMovieDetail: (Int) -> Unit,
    navigateToTvDetail: (Int) -> Unit,
    navigateToPeopleDetail: (Int) -> Unit,
    navigateToCollectionDetail: (Int, String) -> Unit
) {
    MovieList(
        title = "Popular Movies",
        movies = popularMovies,
        navigateToMovieDetail = navigateToMovieDetail
    )
    Spacer(modifier = modifier.height(36.dp))
    HorizontalCollectionList(
        title = "Collections",
        collections = collections,
        navigateToCollectionDetail = navigateToCollectionDetail
    )
    Spacer(modifier = modifier.height(36.dp))
    HorizontalPeopleList(
        title = "Top Actors",
        peoples = popularPeople,
        navigateToPeopleDetail = navigateToPeopleDetail
    )
    Spacer(modifier = modifier.height(36.dp))
    MovieList(
        title = "Popular Tv's",
        movies = popularTvs,
        navigateToMovieDetail = navigateToTvDetail
    )
    Spacer(modifier = modifier.height(16.dp))
}
