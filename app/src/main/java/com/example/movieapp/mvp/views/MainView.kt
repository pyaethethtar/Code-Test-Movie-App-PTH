package com.example.movieapp.mvp.views

import com.example.shared.BaseView
import com.example.shared.data.vos.MovieVO

interface MainView : BaseView {

    fun displayPopularMovies(popularMovies: List<MovieVO>)
    fun displayUpcomingMovies(upcomingMovies : List<MovieVO>)
    fun navigateToMovieDetails(id: Int)

}