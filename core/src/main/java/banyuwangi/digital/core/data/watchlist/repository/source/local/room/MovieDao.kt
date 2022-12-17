package banyuwangi.digital.core.data.watchlist.repository.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import banyuwangi.digital.core.data.watchlist.repository.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getWatchlistMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id=:movieId)")
    suspend fun checkWatchlistMovie(movieId: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id=:movieId)")
    fun getFavoriteMovie(movieId: Int): Flow<Boolean>

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

}