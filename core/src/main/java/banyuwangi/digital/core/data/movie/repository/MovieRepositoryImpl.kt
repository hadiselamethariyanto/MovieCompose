package banyuwangi.digital.core.data.movie.repository

import banyuwangi.digital.core.data.common.ApiResponse
import banyuwangi.digital.core.data.common.NetworkInternetOnlyResource
import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.data.movie.mapper.MovieMapper
import banyuwangi.digital.core.data.movie.repository.source.remote.MovieRemoteDataSource
import banyuwangi.digital.core.data.movie.repository.source.remote.response.DetailMovieResponse
import banyuwangi.digital.core.data.movie.repository.source.remote.response.PopularMovieResponse
import banyuwangi.digital.core.data.movie.repository.source.remote.response.SimilarMovieResponse
import banyuwangi.digital.core.domain.model.DetailMovieDomain
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.core.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MovieRepositoryImpl(private val remoteDataSource: MovieRemoteDataSource) : MoviesRepository {

    override fun getPopularMovie(): Flow<Resource<List<MovieDomain>>> {
        return object : NetworkInternetOnlyResource<List<MovieDomain>, PopularMovieResponse>() {
            override fun loadFromNetwork(data: PopularMovieResponse): Flow<List<MovieDomain>> {
                val response = MovieMapper.moviesItemToDomain(data.results ?: listOf())
                return flowOf(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<PopularMovieResponse>> =
                remoteDataSource.getPopularMovie()

        }.asFlow()
    }

    override fun getDetailMovie(id: Int): Flow<Resource<DetailMovieDomain>> {
        return object : NetworkInternetOnlyResource<DetailMovieDomain, DetailMovieResponse>() {
            override fun loadFromNetwork(data: DetailMovieResponse): Flow<DetailMovieDomain> {
                val response = MovieMapper.detailMovieItemToDomain(data)
                return flowOf(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getDetailMovie(id)

        }.asFlow()
    }

    override fun getSimilarMovie(id: Int): Flow<Resource<List<MovieDomain>>> {
        return object : NetworkInternetOnlyResource<List<MovieDomain>, SimilarMovieResponse>() {
            override fun loadFromNetwork(data: SimilarMovieResponse): Flow<List<MovieDomain>> {
                val response = MovieMapper.moviesItemToDomain(data.results)
                return flowOf(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<SimilarMovieResponse>> =
                remoteDataSource.getSimilarMovie(id)

        }.asFlow()
    }

}