package banyuwangi.digital.moviecompose.ui.screen.detail_collection

import banyuwangi.digital.core.domain.model.MovieDomain

data class DetailCollectionState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val movies: List<MovieDomain> = emptyList()
)