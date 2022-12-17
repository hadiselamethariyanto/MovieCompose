package banyuwangi.digital.core.data.collection.repository

import banyuwangi.digital.core.data.collection.mapper.CollectionMapper
import banyuwangi.digital.core.data.collection.repository.source.remote.CollectionRemoteDataSource
import banyuwangi.digital.core.data.collection.repository.source.remote.response.CollectionDetailResponse
import banyuwangi.digital.core.data.collection.repository.source.remote.response.CollectionResponse
import banyuwangi.digital.core.data.common.ApiResponse
import banyuwangi.digital.core.data.common.NetworkInternetOnlyResource
import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.data.movie.mapper.MovieMapper
import banyuwangi.digital.core.domain.model.CollectionDomain
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.core.domain.repository.CollectionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CollectionRepositoryImpl(private val remoteDataSource: CollectionRemoteDataSource) :
    CollectionRepository {

    override fun getCollection(): Flow<Resource<List<CollectionDomain>>> {
        return object : NetworkInternetOnlyResource<List<CollectionDomain>, CollectionResponse>() {
            override fun loadFromNetwork(data: CollectionResponse): Flow<List<CollectionDomain>> {
                val response = CollectionMapper.mapCollectionItemToDomain(data.results)
                return flowOf(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<CollectionResponse>> =
                remoteDataSource.getCollection()

        }.asFlow()
    }

    override fun getCollectionDetail(id: Int): Flow<Resource<List<MovieDomain>>> {
        return object : NetworkInternetOnlyResource<List<MovieDomain>, CollectionDetailResponse>() {
            override fun loadFromNetwork(data: CollectionDetailResponse): Flow<List<MovieDomain>> {
                val movies = MovieMapper.moviesItemToDomain(data.parts)
                return flowOf(movies)
            }

            override suspend fun createCall(): Flow<ApiResponse<CollectionDetailResponse>> =
                remoteDataSource.getCollectionDetail(id)

        }.asFlow()
    }
}