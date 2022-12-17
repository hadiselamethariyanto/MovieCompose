package banyuwangi.digital.core.domain.model

import banyuwangi.digital.core.data.movie.repository.source.remote.response.GenreItem

data class DetailMovieDomain(
    val id: Int,
    val title: String,
    val backdropPath: String,
    val posterPath:String,
    val genres: List<GenreItem>,
    val overview: String,
    val voteAverage: Double,
    val releaseDate: String,
    val runtime: Int,
    val country: String
)