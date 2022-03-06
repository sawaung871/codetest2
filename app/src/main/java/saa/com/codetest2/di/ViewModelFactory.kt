package saa.com.codetest2.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import saa.com.codetest2.datasource.database.MovieDao
import saa.com.codetest2.repository.Repository
import saa.com.codetest2.ui.MovieListViewModel
import javax.inject.Inject


class ViewModelFactory @Inject constructor(private val movieDao: MovieDao,
    private val repo: Repository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(movieDao,repo) as T
    }
}