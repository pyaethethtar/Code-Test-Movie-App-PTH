package com.example.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movieapp.R
import com.example.movieapp.viewholders.MovieViewHolder
import com.example.shared.BaseAdapter
import com.example.shared.data.vos.MovieVO
import com.example.movieapp.delegates.MoviesDelegate

class MoviesAdapter(val delegate : MoviesDelegate) : BaseAdapter<MovieViewHolder, MovieVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_movie, parent, false)
        return MovieViewHolder(view, delegate)
    }
}