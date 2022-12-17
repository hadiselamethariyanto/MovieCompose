package banyuwangi.digital.core.domain.repository

import banyuwangi.digital.core.domain.model.DetailMovieDomain
import kotlinx.coroutines.flow.Flow

interface WatchlistRepository {

    suspend fun setWatchlistMovie(movie: DetailMovieDomain)

    fun isMovieWatchlist(id:Int):Flow<Boolean>

    fun getWatchlist(): Flow<List<DetailMovieDomain>>
}