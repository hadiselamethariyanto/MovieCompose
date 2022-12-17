package banyuwangi.digital.core.domain.repository

import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.data.tv.repository.source.remote.response.DetailTvResponse
import banyuwangi.digital.core.domain.model.DetailMovieDomain
import banyuwangi.digital.core.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    fun getPopularTv(): Flow<Resource<List<MovieDomain>>>

    fun getDetailTv(tvId: Int): Flow<Resource<DetailMovieDomain>>

    fun getSimilarTv(tvId: Int):Flow<Resource<List<MovieDomain>>>
}