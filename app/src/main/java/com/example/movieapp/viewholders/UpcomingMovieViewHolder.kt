package com.example.movieapp.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.shared.BaseViewHolder
import com.example.shared.data.vos.MovieVO
import com.example.movieapp.delegates.MoviesDelegate
import com.example.shared.utils.IMAGE_URL
import kotlinx.android.synthetic.main.viewholder_upcoming_movie.view.*
import kotlinx.android.synthetic.main.viewholder_upcoming_movie.view.ivMoviePhoto
import kotlinx.android.synthetic.main.viewholder_upcoming_movie.view.tvMovieName

class UpcomingMovieViewHolder(itemView: View, private val mDelegate: MoviesDelegate)
    : BaseViewHolder<MovieVO>(itemView) {


    override fun bindData(data: MovieVO) {
        Glide.with(itemView.context).load(IMAGE_URL +data.posterPath).into(itemView.ivMoviePhoto)
        itemView.tvMovieName.text = data.originalTitle
        itemView.tvMovieDescription.text = data.overview
        itemView.tvPercentage.text = data.voteAverage.toString()

        itemView.setOnClickListener {
            mDelegate.onTapMovieItem(data.id)
        }
        itemView.btnFav.setOnClickListener {
            mDelegate.onTapFavMovie(data)
        }
    }
}