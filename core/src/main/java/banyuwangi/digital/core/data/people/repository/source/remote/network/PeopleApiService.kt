package banyuwangi.digital.core.data.people.repository.source.remote.network

import banyuwangi.digital.core.data.people.repository.source.remote.response.DetailPeopleResponse
import banyuwangi.digital.core.data.people.repository.source.remote.response.MovieCreditResponse
import banyuwangi.digital.core.data.people.repository.source.remote.response.PopularPeopleResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleApiService {

    @GET("3/person/popular")
    suspend fun getPopularPeople(@Query("api_key") apiKey: String): PopularPeopleResponse

    @GET("3/person/{person_id}")
    suspend fun getDetailPeople(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): DetailPeopleResponse

    @GET("3/person/{person_id}/movie_credits")
    suspend fun getMovieCredits(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): MovieCreditResponse

}