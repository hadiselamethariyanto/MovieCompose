package banyuwangi.digital.core.domain.usecase.search

import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.core.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchInteractor(private val repository: SearchRepository) : SearchUseCase {
    override fun searchMovie(query: String): Flow<Resource<List<MovieDomain>>> =
        repository.searchMovie(query)
}