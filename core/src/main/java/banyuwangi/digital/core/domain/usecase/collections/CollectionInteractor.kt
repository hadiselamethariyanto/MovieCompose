package banyuwangi.digital.core.domain.usecase.collections

import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.model.CollectionDomain
import banyuwangi.digital.core.domain.repository.CollectionRepository
import kotlinx.coroutines.flow.Flow

class CollectionInteractor(private val repository: CollectionRepository) : CollectionUseCase {
    override fun getCollections(): Flow<Resource<List<CollectionDomain>>> =
        repository.getCollection()
}