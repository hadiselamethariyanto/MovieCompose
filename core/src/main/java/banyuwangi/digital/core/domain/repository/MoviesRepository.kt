package banyuwangi.digital.core.domain.repository

import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.model.DetailMovieDomain
import banyuwangi.digital.core.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getPopularMovie(): Flow<Resource<List<MovieDomain>>>

    fun getDetailMovie(id: Int): Flow<Resource<DetailMovieDomain>>

    fun getSimilarMovie(id: Int): Flow<Resource<List<MovieDomain>>>

}