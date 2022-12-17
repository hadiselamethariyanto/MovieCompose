package banyuwangi.digital.core.data.people.repository.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailPeopleResponse(
    val id: Int? = null,
    val name: String? = null,
    val biography: String? = null,
    val birthday: String? = null,
    @field:SerializedName("place_of_birth") val placeOfBirth: String? = null,
    @field:SerializedName("profile_path") val profilePath: String? = null
)