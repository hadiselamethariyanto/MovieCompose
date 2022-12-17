package banyuwangi.digital.core.data.people.repository.source.remote.response

import banyuwangi.digital.core.data.movie.repository.source.remote.response.MovieItem

data class MovieCreditResponse(
    val cast: List<MovieItem>
)