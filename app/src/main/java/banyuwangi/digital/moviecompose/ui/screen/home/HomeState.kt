package banyuwangi.digital.moviecompose.ui.screen.home

import banyuwangi.digital.core.domain.model.CollectionDomain
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.core.domain.model.PeopleDomain

data class HomeState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val popularMovies: List<MovieDomain> = emptyList(),
    val popularTv: List<MovieDomain> = emptyList(),
    val collections: List<CollectionDomain> = emptyList(),
    val popularPeople:List<PeopleDomain> = emptyList()
)