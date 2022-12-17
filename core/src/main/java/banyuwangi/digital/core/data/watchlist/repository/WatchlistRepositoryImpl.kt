package banyuwangi.digital.core.data.watchlist.repository

import banyuwangi.digital.core.data.movie.mapper.MovieMapper
import banyuwangi.digital.core.data.watchlist.repository.source.local.MovieLocalDataSource
import banyuwangi.digital.core.domain.model.DetailMovieDomain
import banyuwangi.digital.core.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class WatchlistRepositoryImpl(private val localDataSource: MovieLocalDataSource) :
    WatchlistRepository {

    override suspend fun setWatchlistMovie(movie: DetailMovieDomain) {
        val movieEntity = MovieMapper.mapDomainToEntity(movie)
        localDataSource.setWatchlistMovie(movieEntity)
    }

    override fun isMovieWatchlist(id: Int):Flow<Boolean> {
        return localDataSource.isMovieWatchlist(id)
    }

    override fun getWatchlist(): Flow<List<DetailMovieDomain>> {
        return localDataSource.getWatchlist().map {
            MovieMapper.mapMovieEntityToDomain(it)
        }
    }

}