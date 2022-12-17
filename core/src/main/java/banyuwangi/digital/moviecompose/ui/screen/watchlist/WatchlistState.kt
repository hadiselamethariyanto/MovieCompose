package banyuwangi.digital.moviecompose.ui.screen.watchlist

import banyuwangi.digital.core.domain.model.DetailMovieDomain

data class WatchlistState(val watchlist: List<DetailMovieDomain>? = emptyList())