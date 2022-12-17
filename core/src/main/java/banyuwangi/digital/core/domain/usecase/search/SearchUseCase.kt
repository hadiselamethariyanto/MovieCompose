package banyuwangi.digital.core.domain.usecase.search

import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {

    fun searchMovie(query: String): Flow<Resource<List<MovieDomain>>>
}