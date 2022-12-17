package banyuwangi.digital.moviecompose.ui.screen.detail_people

import banyuwangi.digital.core.domain.model.DetailPeopleDomain
import banyuwangi.digital.core.domain.model.MovieDomain

data class DetailPeopleState(
    val detail: DetailPeopleDomain? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val filmography: List<MovieDomain> = emptyList()
)