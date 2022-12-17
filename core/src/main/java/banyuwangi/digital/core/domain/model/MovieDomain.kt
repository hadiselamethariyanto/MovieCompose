package banyuwangi.digital.core.domain.model

data class MovieDomain(
    val id: Int,
    val title: String,
    val voteAverage: Double,
    val posterPath: String,
    val backdropPath:String,
)