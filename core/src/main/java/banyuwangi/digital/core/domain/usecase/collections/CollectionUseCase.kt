package banyuwangi.digital.core.domain.usecase.collections

import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.model.CollectionDomain
import kotlinx.coroutines.flow.Flow

interface CollectionUseCase {

    fun getCollections(): Flow<Resource<List<CollectionDomain>>>
}