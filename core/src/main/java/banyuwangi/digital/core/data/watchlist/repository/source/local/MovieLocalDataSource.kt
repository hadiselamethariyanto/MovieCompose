package banyuwangi.digital.core.data.watchlist.repository.source.local

import banyuwangi.digital.core.data.watchlist.repository.source.local.entity.MovieEntity
import banyuwangi.digital.core.data.watchlist.repository.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSource(private val movieDao: MovieDao) {

    suspend fun setWatchlistMovie(movie: MovieEntity) {
        if (movieDao.checkWatchlistMovie(movie.id)){
            movieDao.deleteMovie(movie)
        }else{
            movieDao.insertMovie(movie)
        }
    }

    fun isMovieWatchlist(id: Int): Flow<Boolean> {
        return movieDao.getFavoriteMovie(id)
    }

    fun getWatchlist() = movieDao.getWatchlistMovie()
}