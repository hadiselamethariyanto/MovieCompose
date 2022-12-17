package banyuwangi.digital.core.data.people.repository

import banyuwangi.digital.core.data.common.ApiResponse
import banyuwangi.digital.core.data.common.NetworkInternetOnlyResource
import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.data.movie.mapper.MovieMapper
import banyuwangi.digital.core.data.people.mapper.PeopleMapper
import banyuwangi.digital.core.data.people.repository.source.remote.PeopleRemoteDataSource
import banyuwangi.digital.core.data.people.repository.source.remote.response.DetailPeopleResponse
import banyuwangi.digital.core.data.people.repository.source.remote.response.MovieCreditResponse
import banyuwangi.digital.core.data.people.repository.source.remote.response.PopularPeopleResponse
import banyuwangi.digital.core.domain.model.DetailPeopleDomain
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.core.domain.model.PeopleDomain
import banyuwangi.digital.core.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PeopleRepositoryImpl(private val remoteDataSource: PeopleRemoteDataSource) :
    PeopleRepository {
    override fun getPopularPeople(): Flow<Resource<List<PeopleDomain>>> {
        return object : NetworkInternetOnlyResource<List<PeopleDomain>, PopularPeopleResponse>() {
            override fun loadFromNetwork(data: PopularPeopleResponse): Flow<List<PeopleDomain>> {
                val response = PeopleMapper.mapPeopleItemToDomain(data.results)
                return flowOf(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<PopularPeopleResponse>> =
                remoteDataSource.getPopularPeople()

        }.asFlow()
    }

    override fun getDetailPeople(id: Int): Flow<Resource<DetailPeopleDomain>> {
        return object : NetworkInternetOnlyResource<DetailPeopleDomain, DetailPeopleResponse>() {
            override fun loadFromNetwork(data: DetailPeopleResponse): Flow<DetailPeopleDomain> {
                val response = PeopleMapper.mapPeopleResponseToDomain(data)
                return flowOf(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailPeopleResponse>> =
                remoteDataSource.getDetailPeople(id)

        }.asFlow()
    }

    override fun getMovieCredits(id: Int): Flow<Resource<List<MovieDomain>>> {
        return object : NetworkInternetOnlyResource<List<MovieDomain>, MovieCreditResponse>() {
            override fun loadFromNetwork(data: MovieCreditResponse): Flow<List<MovieDomain>> {
                val response = MovieMapper.moviesItemToDomain(data.cast)
                return flowOf(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieCreditResponse>> =
                remoteDataSource.getMovieCredits(id)

        }.asFlow()
    }
}