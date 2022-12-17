package banyuwangi.digital.core.domain.repository

import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun searchMovie(query: String): Flow<Resource<List<MovieDomain>>>
}