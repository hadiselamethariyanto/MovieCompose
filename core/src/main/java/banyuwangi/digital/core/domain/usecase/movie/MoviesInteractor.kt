package banyuwangi.digital.core.domain.usecase.movie

import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.model.DetailMovieDomain
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.core.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesInteractor(private val repository: MoviesRepository) : MoviesUseCase {
    override fun getPopularMovie(): Flow<Resource<List<MovieDomain>>> = repository.getPopularMovie()
    override fun getDetailMovie(id: Int): Flow<Resource<DetailMovieDomain>> =
        repository.getDetailMovie(id)

    override fun getSimilarMovie(id: Int): Flow<Resource<List<MovieDomain>>> =
        repository.getSimilarMovie(id)
}