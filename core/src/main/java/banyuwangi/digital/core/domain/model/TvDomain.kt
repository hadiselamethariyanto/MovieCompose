package banyuwangi.digital.core.domain.model

data class TvDomain(
    val id: Int,
    val name: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double
)