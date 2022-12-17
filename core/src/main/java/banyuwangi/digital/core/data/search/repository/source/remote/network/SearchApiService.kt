package banyuwangi.digital.core.data.search.repository.source.remote.network

import banyuwangi.digital.core.data.search.repository.source.remote.response.SearchMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("3/search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String
    ): SearchMovieResponse


}