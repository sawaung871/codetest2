package saa.com.codetest2.repository

import android.util.Log
import com.google.gson.Gson
import saa.com.codetest2.datasource.network.retrofit.ServiceApi
import saa.com.codetest2.model.Movie
import saa.com.codetest2.model.ServerResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val serviceApi: ServiceApi) {

    suspend fun callPopularMovies(apiKey: String): ServerResult<List<Movie>> {
        var result = serviceApi.getPopularMovie(apiKey)
        Log.i("Repository", Gson().toJson(result))
        return result
    }

    suspend fun callUpcomingMovies(apiKey: String): ServerResult<List<Movie>> {
        var result = serviceApi.getUpcomingMovie(apiKey)
        Log.i("Repository upcoming", Gson().toJson(result))
        return result
    }
}