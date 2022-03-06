package saa.com.codetest2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class Movie(
    var adult: Boolean,
    var backdrop_path: String,
    //var genre_ids: List<Int>,
    @PrimaryKey var id: Int,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var release_date: String,
    var title: String,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int
)