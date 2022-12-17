package banyuwangi.digital.core.data.collection.repository.source.remote.response

import com.google.gson.annotations.SerializedName

data class CollectionResponse(
    val results: List<CollectionItem>
)

data class CollectionItem(
    val id: Int,
    @field:SerializedName("backdrop_path") val backdropPath: String? = null,
    val name: String
)