package saa.com.codetest2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import saa.com.codetest2.R
import saa.com.codetest2.datasource.network.NetworkState
import saa.com.codetest2.di.AppController
import saa.com.codetest2.di.ViewModelFactory
import saa.com.codetest2.model.Movie
import javax.inject.Inject

class MovieList : AppCompatActivity() {

    private lateinit var viewModel: MovieListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        (application as AppController).appComponent.inject(this)

        viewModel =ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)

        viewModel.callPopularMovies()
        //viewModel.callUpcomingMovies()
        //viewModel.callApi()

        viewModel.networkState.observe(this, Observer<NetworkState> {

            Log.i("MovieList", it.toString())

            if(it == NetworkState.LOADED){
                viewModel.getMovieList()
            }
        })

    }
}