package banyuwangi.digital.moviecompose.ui.screen.search

import banyuwangi.digital.core.domain.model.MovieDomain

data class SearchState(
    val isLoading: Boolean = false,
    val movies: List<MovieDomain> = emptyList(),
    val error: String? = null,
    val searchQuery: String = ""
)