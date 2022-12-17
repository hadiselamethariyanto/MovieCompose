package banyuwangi.digital.moviecompose.ui.screen.detail_movie

import banyuwangi.digital.core.domain.model.DetailMovieDomain
import banyuwangi.digital.core.domain.model.MovieDomain

data class DetailMovieState(
    val detailMovie: DetailMovieDomain? = null,
    val similarMovies: List<MovieDomain> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isMovieWatchlist:Boolean = false
)