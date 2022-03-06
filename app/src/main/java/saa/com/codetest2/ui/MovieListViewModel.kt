package saa.com.codetest2.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import saa.com.codetest2.datasource.database.MovieDao
import saa.com.codetest2.datasource.network.NetworkState
import saa.com.codetest2.model.Movie
import saa.com.codetest2.model.ServerResult
import saa.com.codetest2.repository.Repository
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val movieDao: MovieDao,private val repository: Repository) :
    ViewModel() {

    companion object{
        const val TAG = "MovieListViewModel"
    }

    private lateinit var serverResultPopularMovies :LiveData<List<Movie>>
    var serverResultUpcomingMovies = MutableLiveData<ServerResult<List<Movie>>>()
    var networkState = MutableLiveData<NetworkState>()




    fun callPopularMovies() {
        Log.d(TAG, "call popular movie")

        networkState.postValue(NetworkState.LOADING)

        viewModelScope.launch (Dispatchers.IO){
            try {

                //serverResultPopularMovies.postValue(repository.callPopularMovies("4d0dc249f21cc4fa64ec5582a7d622fb"))

                //serverResultUpcomingMovies.postValue(repository.callUpcomingMovies("4d0dc249f21cc4fa64ec5582a7d622fb"))

                var popularMovieList = repository.callPopularMovies("4d0dc249f21cc4fa64ec5582a7d622fb")
                var upcomingMovieList = repository.callUpcomingMovies("4d0dc249f21cc4fa64ec5582a7d622fb")


                insertIntoDB(popularMovieList)

                networkState.postValue(NetworkState.LOADED)

            }catch (e:Exception){
                e.printStackTrace()
                networkState.postValue(NetworkState.error(e.message))
            }
        }

    }

    private fun insertIntoDB(result:ServerResult<List<Movie>>){

        GlobalScope.launch( Dispatchers.IO) {
            Log.i(TAG, "insert to db -> " + result.results.size)
            movieDao.insertAll(result.results)
            Log.i(TAG, "size of list -> "+ (movieDao.getMovies()?.value?.get(0)?.id ?: -1))
        }
    }

    fun getMovieList(){
        serverResultPopularMovies = movieDao.getMovies()
        Log.i(TAG, "size of list -> "+ serverResultPopularMovies.value?.size)
    }

    fun callUpcomingMovies(): LiveData<ServerResult<List<Movie>>> {
        Log.d(TAG, "call upcoming movie")
        var serverResult = MutableLiveData<ServerResult<List<Movie>>>()
        viewModelScope.launch (Dispatchers.IO){
            try {
                serverResult.postValue(repository.callUpcomingMovies("4d0dc249f21cc4fa64ec5582a7d622fb"))
            }catch (e:Exception){
                e.printStackTrace()
                serverResult.postValue(null)
            }
        }
        return  serverResult
    }


}