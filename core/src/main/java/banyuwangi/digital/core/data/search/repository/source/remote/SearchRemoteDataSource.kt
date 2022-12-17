package banyuwangi.digital.core.data.search.repository.source.remote

import banyuwangi.digital.core.BuildConfig
import banyuwangi.digital.core.data.common.ApiResponse
import banyuwangi.digital.core.data.search.repository.source.remote.network.SearchApiService
import banyuwangi.digital.core.data.search.repository.source.remote.response.SearchMovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRemoteDataSource(private val service: SearchApiService) {

    suspend fun searchMovie(query: String): Flow<ApiResponse<SearchMovieResponse>> {
        return flow {
            try {
                val response = service.searchMovie(query, BuildConfig.API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}