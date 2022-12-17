package banyuwangi.digital.core.domain.repository

import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.model.CollectionDomain
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {

    fun getCollection(): Flow<Resource<List<CollectionDomain>>>
}