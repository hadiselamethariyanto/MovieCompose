package banyuwangi.digital.core.domain.model

data class DetailPeopleDomain(
    val id: Int,
    val name: String,
    val biography: String,
    val birthday: String,
    val placeOfBirth: String,
    val profilePath: String
)