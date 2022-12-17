package banyuwangi.digital.core.data.movie.mapper

import banyuwangi.digital.core.data.movie.repository.source.remote.response.DetailMovieResponse
import banyuwangi.digital.core.data.movie.repository.source.remote.response.GenreItem
import banyuwangi.digital.core.data.movie.repository.source.remote.response.MovieItem
import banyuwangi.digital.core.data.watchlist.repository.source.local.entity.MovieEntity
import banyuwangi.digital.core.domain.model.DetailMovieDomain
import banyuwangi.digital.core.domain.model.MovieDomain

object MovieMapper {

    fun mapDomainToEntity(movie: DetailMovieDomain): MovieEntity = MovieEntity(
        id = movie.id,
        title = movie.title,
        overview = movie.overview,
        voteAverage = movie.voteAverage,
        genres = movie.genres.joinToString(separator = ", ") { it.name },
        posterPath = movie.posterPath,
        releaseDate = movie.releaseDate,
        runtime = movie.runtime,
        country = movie.country
    )

    fun mapMovieEntityToDomain(movies: List<MovieEntity>): List<DetailMovieDomain> = movies.map {
        DetailMovieDomain(
            id = it.id,
            title = it.title,
            overview = it.overview,
            voteAverage = it.voteAverage,
            genres = it.genres.split(",").toTypedArray()
                .mapIndexed { index, name -> GenreItem(index, name) },
            backdropPath = it.posterPath,
            releaseDate = it.releaseDate,
            runtime = it.runtime,
            country = it.country,
            posterPath = it.posterPath
        )
    }

    fun moviesItemToDomain(list: List<MovieItem>): List<MovieDomain> = list.map {
        MovieDomain(
            id = it.id ?: 0,
            title = it.title ?: "",
            posterPath = it.posterPath ?: "",
            backdropPath = it.backdropPath ?: "",
            voteAverage = it.voteAverage ?: 0.0
        )
    }

    fun detailMovieItemToDomain(detailMovie: DetailMovieResponse?): DetailMovieDomain {
        val countries = detailMovie?.productionCountries ?: arrayListOf()
        var country = ""
        if (countries.isNotEmpty()) {
            country = countries[0].name
        }
        return DetailMovieDomain(
            id = detailMovie?.id ?: 0,
            title = detailMovie?.title ?: "",
            backdropPath = detailMovie?.backdropPath ?: "",
            genres = detailMovie?.genres ?: arrayListOf(),
            overview = detailMovie?.overview ?: "",
            voteAverage = detailMovie?.voteAverage ?: 0.0,
            releaseDate = detailMovie?.releaseDate ?: "1999-10-12",
            runtime = detailMovie?.runtime ?: 0,
            country = country,
            posterPath = detailMovie?.posterPath ?: ""
        )
    }

}