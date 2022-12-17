package banyuwangi.digital.core.data.watchlist.repository.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import banyuwangi.digital.core.data.watchlist.repository.source.local.entity.MovieEntity


@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}