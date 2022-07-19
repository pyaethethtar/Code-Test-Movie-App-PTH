package com.example.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.MovieVO

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieVO>>

    @Query("SELECT * FROM movies WHERE id= :id")
    fun getMovieById(id: Int): LiveData<MovieVO>

    @Query("SELECT isFavourite FROM movies WHERE id= :id")
    fun getFavouriteStatusById(id: Int): Boolean

    @Query("SELECT isPopular FROM movies WHERE id= :id")
    fun getPopularStatusById(id: Int) : Boolean

    @Query("SELECT isUpcoming FROM movies WHERE id= :id")
    fun getUpcomingStatusById(id: Int) : Boolean

    @Query("DELETE FROM movies")
    fun deleteAllMovies()

    @Delete
    fun deleteMovie(movie: MovieVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieVO)
}