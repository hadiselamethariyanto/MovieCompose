package banyuwangi.digital.core.domain.usecase.tv

import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.model.DetailMovieDomain
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.core.domain.repository.TvRepository
import kotlinx.coroutines.flow.Flow

class TvInteractor(private val tvRepository: TvRepository) : TvUseCase {

    override fun getPopularTv(): Flow<Resource<List<MovieDomain>>> = tvRepository.getPopularTv()

    override fun getDetailTv(tvId: Int): Flow<Resource<DetailMovieDomain>> =
        tvRepository.getDetailTv(tvId)

    override fun getSimilarTv(tvId: Int): Flow<Resource<List<MovieDomain>>> =
        tvRepository.getSimilarTv(tvId)
}