package banyuwangi.digital.core.data.collection.repository.source.remote.response

import banyuwangi.digital.core.data.movie.repository.source.remote.response.MovieItem

data class CollectionDetailResponse(
    val parts: List<MovieItem> = emptyList()
)
