package com.example.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shared.data.vos.GenresVO

@Dao
interface GenresDao {

    @Query("SELECT * FROM genres")
    fun getGenresList() : LiveData<List<GenresVO>>

    @Query("SELECT * FROM genres WHERE id= :id")
    fun getGenresById(id: Int): LiveData<GenresVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenresList(genres : List<GenresVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(genres: GenresVO)

}