package banyuwangi.digital.core.data.collection.mapper

import banyuwangi.digital.core.data.collection.repository.source.remote.response.CollectionItem
import banyuwangi.digital.core.domain.model.CollectionDomain

object CollectionMapper {

    fun mapCollectionItemToDomain(collections: List<CollectionItem>): List<CollectionDomain> =
        collections.map {
            CollectionDomain(
                id = it.id,
                backdropPath = it.backdropPath ?: "",
                name = it.name
            )
        }
}