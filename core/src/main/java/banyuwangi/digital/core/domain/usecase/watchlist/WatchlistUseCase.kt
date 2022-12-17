package banyuwangi.digital.core.domain.usecase.watchlist

import banyuwangi.digital.core.domain.model.DetailMovieDomain
import kotlinx.coroutines.flow.Flow

interface WatchlistUseCase {

    suspend fun setWatchlistMovie(movie: DetailMovieDomain)

    fun isMovieWatchlist(id:Int):Flow<Boolean>

    fun getWatchlist(): Flow<List<DetailMovieDomain>>

}