package com.example.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.MovieVO

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies Where isPopular='true'")
    fun getPopularMovies(): LiveData<List<MovieVO>>

    @Query("SELECT * FROM movies Where isUpcoming='true'")
    fun getUpcomingMovies(): LiveData<List<MovieVO>>

    @Query("SELECT * FROM movies WHERE id= :id")
    fun getMovieById(id: Int): LiveData<MovieVO>

    @Query("DELETE FROM movies")
    fun deleteAllMovies()

    @Delete
    fun deleteMovie(movie: MovieVO)

    @Insert
    fun insertAllMovies(movies: List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieVO)
}