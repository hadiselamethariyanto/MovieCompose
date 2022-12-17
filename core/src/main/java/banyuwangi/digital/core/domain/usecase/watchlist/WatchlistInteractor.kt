package banyuwangi.digital.core.domain.usecase.watchlist

import banyuwangi.digital.core.domain.model.DetailMovieDomain
import banyuwangi.digital.core.domain.repository.WatchlistRepository

class WatchlistInteractor(private val repository: WatchlistRepository) : WatchlistUseCase {
    override suspend fun setWatchlistMovie(movie: DetailMovieDomain) = repository.setWatchlistMovie(movie)

    override fun isMovieWatchlist(id: Int) = repository.isMovieWatchlist(id)

    override fun getWatchlist() = repository.getWatchlist()
}