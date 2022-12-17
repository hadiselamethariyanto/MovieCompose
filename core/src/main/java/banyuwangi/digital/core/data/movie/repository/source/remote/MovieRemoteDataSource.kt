package banyuwangi.digital.core.data.movie.repository.source.remote

import banyuwangi.digital.core.data.common.ApiResponse
import banyuwangi.digital.core.data.movie.repository.source.remote.network.MovieApiService
import banyuwangi.digital.core.data.movie.repository.source.remote.response.PopularMovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import banyuwangi.digital.core.BuildConfig
import banyuwangi.digital.core.data.movie.repository.source.remote.response.DetailMovieResponse
import banyuwangi.digital.core.data.movie.repository.source.remote.response.SimilarMovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class MovieRemoteDataSource(private val movieApiService: MovieApiService) {

    suspend fun getPopularMovie(): Flow<ApiResponse<PopularMovieResponse>> {
        return flow {
            try {
                val service = movieApiService.getPopularMovies(API_KEY, 1)
                emit(ApiResponse.Success(service))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMovie(id: Int): Flow<ApiResponse<DetailMovieResponse>> {
        return flow {
            try {
                val response = movieApiService.getDetailMovie(id, API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSimilarMovie(id: Int): Flow<ApiResponse<SimilarMovieResponse>> {
        return flow {
            try {
                val response = movieApiService.getSimilarMovie(id, API_KEY, 1)
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