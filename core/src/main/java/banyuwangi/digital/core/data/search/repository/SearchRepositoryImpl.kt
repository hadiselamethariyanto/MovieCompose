package banyuwangi.digital.core.data.search.repository

import banyuwangi.digital.core.data.common.ApiResponse
import banyuwangi.digital.core.data.common.NetworkInternetOnlyResource
import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.data.movie.mapper.MovieMapper
import banyuwangi.digital.core.data.search.repository.source.remote.SearchRemoteDataSource
import banyuwangi.digital.core.data.search.repository.source.remote.response.SearchMovieResponse
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.core.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SearchRepositoryImpl(private val remoteDataSource: SearchRemoteDataSource) :
    SearchRepository {

    override fun searchMovie(query: String): Flow<Resource<List<MovieDomain>>> {
        return object : NetworkInternetOnlyResource<List<MovieDomain>, SearchMovieResponse>() {
            override fun loadFromNetwork(data: SearchMovieResponse): Flow<List<MovieDomain>> {
                val movies = MovieMapper.moviesItemToDomain(data.results)
                return flowOf(movies)
            }

            override suspend fun createCall(): Flow<ApiResponse<SearchMovieResponse>> =
                remoteDataSource.searchMovie(query)

        }.asFlow()
    }

}