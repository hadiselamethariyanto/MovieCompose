package banyuwangi.digital.core.data.tv.repository

import banyuwangi.digital.core.data.common.ApiResponse
import banyuwangi.digital.core.data.common.NetworkInternetOnlyResource
import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.data.tv.mapper.TvMapper
import banyuwangi.digital.core.data.tv.repository.source.remote.TvRemoteDataSource
import banyuwangi.digital.core.data.tv.repository.source.remote.response.DetailTvResponse
import banyuwangi.digital.core.data.tv.repository.source.remote.response.SimilarTvResponse
import banyuwangi.digital.core.data.tv.repository.source.remote.response.TvPopularResponse
import banyuwangi.digital.core.domain.model.DetailMovieDomain
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.core.domain.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TvRepositoryImpl(private val remoteDataSource: TvRemoteDataSource) : TvRepository {
    override fun getPopularTv(): Flow<Resource<List<MovieDomain>>> {
        return object : NetworkInternetOnlyResource<List<MovieDomain>, TvPopularResponse>() {
            override fun loadFromNetwork(data: TvPopularResponse): Flow<List<MovieDomain>> {
                val response = TvMapper.mapTvItemToDomain(data.results)
                return flowOf(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<TvPopularResponse>> =
                remoteDataSource.getPopularTv()

        }.asFlow()
    }

    override fun getDetailTv(tvId: Int): Flow<Resource<DetailMovieDomain>> {
        return object : NetworkInternetOnlyResource<DetailMovieDomain, DetailTvResponse>() {
            override fun loadFromNetwork(data: DetailTvResponse): Flow<DetailMovieDomain> {
                val response = TvMapper.mapTvDetailResponseToDomain(data)
                return flowOf(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailTvResponse>> =
                remoteDataSource.getDetailTv(tvId)

        }.asFlow()
    }

    override fun getSimilarTv(tvId: Int): Flow<Resource<List<MovieDomain>>> {
        return object : NetworkInternetOnlyResource<List<MovieDomain>, SimilarTvResponse>() {
            override fun loadFromNetwork(data: SimilarTvResponse): Flow<List<MovieDomain>> {
                val response = TvMapper.mapTvItemToDomain(data.results)
                return flowOf(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<SimilarTvResponse>> =
                remoteDataSource.getSimilarTv(tvId)

        }.asFlow()
    }
}