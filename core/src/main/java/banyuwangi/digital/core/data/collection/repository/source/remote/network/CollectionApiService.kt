package banyuwangi.digital.core.data.collection.repository.source.remote.network

import banyuwangi.digital.core.data.collection.repository.source.remote.response.CollectionDetailResponse
import banyuwangi.digital.core.data.collection.repository.source.remote.response.CollectionResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionApiService {

    @GET("3/search/collection")
    suspend fun getCollections(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): CollectionResponse

    @GET("3/collection/{collection_id}")
    suspend fun getCollectionDetail(
        @Path("collection_id") collectionId: Int,
        @Query("api_key") apiKey: String
    ): CollectionDetailResponse

}