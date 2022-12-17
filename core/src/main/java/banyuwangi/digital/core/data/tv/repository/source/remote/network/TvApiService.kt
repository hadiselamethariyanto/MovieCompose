package banyuwangi.digital.core.data.tv.repository.source.remote.network

import banyuwangi.digital.core.data.tv.repository.source.remote.response.DetailTvResponse
import banyuwangi.digital.core.data.tv.repository.source.remote.response.SimilarTvResponse
import banyuwangi.digital.core.data.tv.repository.source.remote.response.TvPopularResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvApiService {
    @GET("3/tv/popular")
    suspend fun getPopularTv(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): TvPopularResponse

    @GET("3/tv/{tv_id}")
    suspend fun getDetailTv(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): DetailTvResponse

    @GET("3/tv/{tv_id}/similar")
    suspend fun getSimilarTv(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): SimilarTvResponse
}