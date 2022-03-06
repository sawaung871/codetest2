package saa.com.codetest2.datasource.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import saa.com.codetest2.model.Movie


@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie ORDER BY release_date")
    fun getMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM Movie WHERE id = :id ORDER BY release_date")
    fun getMovieById(id: String): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movie: List<Movie>)
}