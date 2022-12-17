package banyuwangi.digital.core.data.movie.repository.source.remote.response

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(val results: List<MovieItem>? = listOf())

data class MovieItem(
    val id: Int? = null,
    val title: String? = null,
    @field:SerializedName("poster_path")
    val posterPath: String? = null,
    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,
    val overview: String? = null,
    @field:SerializedName("vote_count")
    val voteCount: Int? = null,
    @field:SerializedName("vote_average")
    val voteAverage: Double? = null
)