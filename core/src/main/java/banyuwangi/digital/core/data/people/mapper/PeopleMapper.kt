package banyuwangi.digital.core.data.people.mapper

import banyuwangi.digital.core.data.people.repository.source.remote.response.DetailPeopleResponse
import banyuwangi.digital.core.data.people.repository.source.remote.response.PeopleItem
import banyuwangi.digital.core.domain.model.DetailPeopleDomain
import banyuwangi.digital.core.domain.model.PeopleDomain

object PeopleMapper {

    fun mapPeopleItemToDomain(peoples: List<PeopleItem>): List<PeopleDomain> = peoples.map {
        PeopleDomain(
            id = it.id,
            name = it.name,
            profilePath = it.profilePath
        )
    }

    fun mapPeopleResponseToDomain(detail: DetailPeopleResponse): DetailPeopleDomain =
        DetailPeopleDomain(
            id = detail.id ?: 0,
            name = detail.name ?: "",
            biography = detail.biography ?: "",
            birthday = detail.birthday ?: "",
            placeOfBirth = detail.placeOfBirth ?: "",
            profilePath = detail.profilePath ?: ""
        )

}