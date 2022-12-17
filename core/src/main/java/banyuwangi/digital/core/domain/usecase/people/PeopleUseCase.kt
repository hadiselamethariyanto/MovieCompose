package banyuwangi.digital.core.domain.usecase.people

import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.model.DetailPeopleDomain
import banyuwangi.digital.core.domain.model.MovieDomain
import banyuwangi.digital.core.domain.model.PeopleDomain
import kotlinx.coroutines.flow.Flow

interface PeopleUseCase {

    fun getPopularPeople(): Flow<Resource<List<PeopleDomain>>>

    fun getDetailPeople(id: Int): Flow<Resource<DetailPeopleDomain>>

    fun getMovieCredits(id:Int):Flow<Resource<List<MovieDomain>>>
}