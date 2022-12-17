package banyuwangi.digital.core.data.tv.repository.source.remote

import banyuwangi.digital.core.BuildConfig
import banyuwangi.digital.core.data.common.ApiResponse
import banyuwangi.digital.core.data.tv.repository.source.remote.network.TvApiService
import banyuwangi.digital.core.data.tv.repository.source.remote.response.DetailTvResponse
import banyuwangi.digital.core.data.tv.repository.source.remote.response.SimilarTvResponse
import banyuwangi.digital.core.data.tv.repository.source.remote.response.TvPopularResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TvRemoteDataSource(private val tvApiService: TvApiService) {

    suspend fun getPopularTv(): Flow<ApiResponse<TvPopularResponse>> {
        return flow {
            try {
                val response = tvApiService.getPopularTv(API_KEY, 1)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailTv(tvId: Int): Flow<ApiResponse<DetailTvResponse>> {
        return flow {
            try {
                val response = tvApiService.getDetailTv(tvId, API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSimilarTv(tvId: Int): Flow<ApiResponse<SimilarTvResponse>> {
        return flow {
            try {
                val response = tvApiService.getSimilarTv(tvId, API_KEY)
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