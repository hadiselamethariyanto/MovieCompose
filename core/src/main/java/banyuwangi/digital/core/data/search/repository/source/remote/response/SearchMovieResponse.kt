package banyuwangi.digital.core.data.search.repository.source.remote.response

import banyuwangi.digital.core.data.movie.repository.source.remote.response.MovieItem

data class SearchMovieResponse(
    val results:List<MovieItem>
)
