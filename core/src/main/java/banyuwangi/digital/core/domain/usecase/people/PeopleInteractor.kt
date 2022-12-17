package banyuwangi.digital.core.domain.usecase.people

import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.model.DetailPeopleDomain
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.core.domain.model.PeopleDomain
import banyuwangi.digital.core.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow

class PeopleInteractor(private val repository: PeopleRepository) : PeopleUseCase {
    override fun getPopularPeople(): Flow<Resource<List<PeopleDomain>>> =
        repository.getPopularPeople()

    override fun getDetailPeople(id: Int): Flow<Resource<DetailPeopleDomain>> =
        repository.getDetailPeople(id)

    override fun getMovieCredits(id: Int): Flow<Resource<List<MovieDomain>>> =
        repository.getMovieCredits(id)
}