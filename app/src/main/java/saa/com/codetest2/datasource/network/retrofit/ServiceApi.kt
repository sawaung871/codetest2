package saa.com.codetest2.datasource.network.retrofit

import saa.com.codetest2.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query
import saa.com.codetest2.model.ServerResult
import saa.com.codetest2.util.URL


interface ServiceApi {

    @GET(URL + "3/movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey:String,
    ): ServerResult<List<Movie>>

    @GET(URL + "3/movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("api_key") apiKey:String,
    ): ServerResult<List<Movie>>

}
