package banyuwangi.digital.core.data.tv.mapper

import banyuwangi.digital.core.data.tv.repository.source.remote.response.DetailTvResponse
import banyuwangi.digital.core.data.tv.repository.source.remote.response.TvItem
import banyuwangi.digital.core.domain.model.DetailMovieDomain
import banyuwangi.digital.core.domain.model.MovieDomain

object TvMapper {

    fun mapTvItemToDomain(list: List<TvItem>): List<MovieDomain> = list.map {
        MovieDomain(
            id = it.id ?: 0,
            title = it.name ?: "",
            posterPath = it.posterPath ?: "",
            backdropPath = it.backdropPath ?: "",
            voteAverage = it.voteAverage ?: 0.0
        )
    }

    fun mapTvDetailResponseToDomain(detail: DetailTvResponse?): DetailMovieDomain {
        val countries = detail?.productionCountries ?: arrayListOf()
        var country = ""
        if (countries.isNotEmpty()) {
            country = countries[0].name
        }
        return DetailMovieDomain(
            id = detail?.id ?: 0,
            title = detail?.name ?: "",
            backdropPath = detail?.backdropPath ?: "",
            genres = detail?.genres ?: arrayListOf(),
            overview = detail?.overview ?: "",
            voteAverage = detail?.voteAverage ?: 0.0,
            releaseDate = detail?.firstAirDate ?: "",
            runtime = 0,
            country = country,
            posterPath = detail?.posterPath?:""
        )
    }
}