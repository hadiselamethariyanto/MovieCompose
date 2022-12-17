package banyuwangi.digital.core.data.movie.repository.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    val id: Int? = null,
    val title: String? = null,
    @field:SerializedName("backdrop_path") val backdropPath: String? = null,
    @field:SerializedName("poster_path") val posterPath:String?=null,
    val genres: List<GenreItem>? = arrayListOf(),
    val overview: String? = null,
    @field:SerializedName("vote_average") val voteAverage: Double? = null,
    @field:SerializedName("release_date") val releaseDate: String? = null,
    val runtime: Int? = null,
    @field:SerializedName("production_countries") val productionCountries: List<ProductionCountries>?= arrayListOf()
)

data class ProductionCountries(
    val name: String
)
