package banyuwangi.digital.core.data.people.repository.source.remote

import banyuwangi.digital.core.BuildConfig
import banyuwangi.digital.core.data.common.ApiResponse
import banyuwangi.digital.core.data.people.repository.source.remote.network.PeopleApiService
import banyuwangi.digital.core.data.people.repository.source.remote.response.DetailPeopleResponse
import banyuwangi.digital.core.data.people.repository.source.remote.response.MovieCreditResponse
import banyuwangi.digital.core.data.people.repository.source.remote.response.PopularPeopleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PeopleRemoteDataSource(private val service: PeopleApiService) {

    suspend fun getPopularPeople(): Flow<ApiResponse<PopularPeopleResponse>> {
        return flow {
            try {
                val response = service.getPopularPeople(API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailPeople(id: Int): Flow<ApiResponse<DetailPeopleResponse>> {
        return flow {
            try {
                val response = service.getDetailPeople(id, API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieCredits(id: Int): Flow<ApiResponse<MovieCreditResponse>> {
        return flow {
            try {
                val response = service.getMovieCredits(id, API_KEY)
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