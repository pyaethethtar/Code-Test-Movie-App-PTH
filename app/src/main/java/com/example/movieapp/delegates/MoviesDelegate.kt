package com.example.movieapp.delegates

import com.example.shared.data.vos.MovieVO

interface MoviesDelegate {

    fun onTapMovieItem(id: Int)
    fun onTapFavMovie(movie: MovieVO)

}