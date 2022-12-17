package banyuwangi.digital.core.data.tv.repository.source.remote.response

import banyuwangi.digital.core.data.movie.repository.source.remote.response.GenreItem
import banyuwangi.digital.core.data.movie.repository.source.remote.response.ProductionCountries
import com.google.gson.annotations.SerializedName

data class DetailTvResponse(
    val id: Int,
    val name: String,
    @field:SerializedName("backdrop_path") val backdropPath: String,
    @field:SerializedName("poster_path") val posterPath: String,
    val genres: List<GenreItem>? = arrayListOf(),
    val overview: String? = null,
    @field:SerializedName("vote_average") val voteAverage: Double? = null,
    @field:SerializedName("first_air_date") val firstAirDate: String? = null,
    @field:SerializedName("number_of_episodes") val numberOfEpisodes: Int? = null,
    @field:SerializedName("number_of_seasons") val numberOfSeasons: Int? = null,
    @field:SerializedName("production_countries") val productionCountries: List<ProductionCountries>? = arrayListOf()
)
