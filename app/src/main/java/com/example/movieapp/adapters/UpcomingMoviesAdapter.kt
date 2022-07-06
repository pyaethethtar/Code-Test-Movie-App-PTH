package com.example.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movieapp.R
import com.example.movieapp.viewholders.UpcomingMovieViewHolder
import com.example.shared.BaseAdapter
import com.example.shared.data.vos.MovieVO
import com.example.movieapp.delegates.MoviesDelegate

class UpcomingMoviesAdapter(val delegate: MoviesDelegate) : BaseAdapter<UpcomingMovieViewHolder, MovieVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_upcoming_movie, parent, false)
        return UpcomingMovieViewHolder(view, delegate)
    }
}