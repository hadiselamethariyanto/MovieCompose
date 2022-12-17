package banyuwangi.digital.core.data.tv.repository.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvPopularResponse(
    val results: List<TvItem>
)

data class TvItem(
    val id: Int? = null,
    val name: String? = null,
    @field:SerializedName("poster_path")
    val posterPath: String? = null,
    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @field:SerializedName("vote_average")
    val voteAverage: Double? = null
)