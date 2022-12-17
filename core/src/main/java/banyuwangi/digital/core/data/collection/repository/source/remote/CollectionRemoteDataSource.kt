package banyuwangi.digital.core.data.collection.repository.source.remote

import banyuwangi.digital.core.BuildConfig
import banyuwangi.digital.core.data.collection.repository.source.remote.network.CollectionApiService
import banyuwangi.digital.core.data.collection.repository.source.remote.response.CollectionDetailResponse
import banyuwangi.digital.core.data.collection.repository.source.remote.response.CollectionResponse
import banyuwangi.digital.core.data.common.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CollectionRemoteDataSource(private val service: CollectionApiService) {

    suspend fun getCollection(): Flow<ApiResponse<CollectionResponse>> {
        return flow {
            try {
                val response = service.getCollections(API_KEY, "avengers")
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCollectionDetail(id: Int): Flow<ApiResponse<CollectionDetailResponse>> {
        return flow {
            try {
                val response = service.getCollectionDetail(id, API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }
}