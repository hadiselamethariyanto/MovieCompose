package banyuwangi.digital.core.data.movie.repository.source.remote.network

import banyuwangi.digital.core.data.movie.repository.source.remote.response.DetailMovieResponse
import banyuwangi.digital.core.data.movie.repository.source.remote.response.PopularMovieResponse
import banyuwangi.digital.core.data.movie.repository.source.remote.response.SimilarMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int?
    ): PopularMovieResponse

    @GET("3/movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): DetailMovieResponse

    @GET("3/movie/{movie_id}/similar")
    suspend fun getSimilarMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): SimilarMovieResponse

}