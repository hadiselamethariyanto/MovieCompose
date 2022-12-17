package banyuwangi.digital.core.data.people.repository.source.remote.response

import com.google.gson.annotations.SerializedName

data class PopularPeopleResponse(
    val results: List<PeopleItem>
)

data class PeopleItem(
    val id: Int,
    val name: String,
    @field:SerializedName("profile_path") val profilePath: String
)