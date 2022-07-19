package com.example.shared.data.model


import androidx.lifecycle.LiveData
import com.example.shared.data.vos.GenresVO
import com.example.shared.data.vos.MovieVO
import com.example.shared.data.vos.PersonVO

interface MovieModel {

    fun getMoviesFromApiAndSaveToDatabase(onSuccess: () -> Unit, onError: (String) -> Unit)
    fun getAllMovies(onError: (String) -> Unit): LiveData<List<MovieVO>>
    fun getMovieById(id:Int, onError: (String) -> Unit): LiveData<MovieVO>
    fun getActorsList(onError: (String) -> Unit) : LiveData<List<PersonVO>>
    fun getGenresList() : LiveData<List<GenresVO>>
    fun setFavouriteMovie(movie: MovieVO)
}