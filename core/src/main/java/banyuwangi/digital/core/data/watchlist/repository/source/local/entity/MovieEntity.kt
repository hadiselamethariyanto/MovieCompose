package banyuwangi.digital.core.data.watchlist.repository.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double,
    @ColumnInfo(name = "genres")
    var genres: String,
    @ColumnInfo(name = "poster_path")
    var posterPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "runtime")
    val runtime: Int,
    @ColumnInfo(name = "country")
    val country: String
)