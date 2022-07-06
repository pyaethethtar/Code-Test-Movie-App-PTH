package com.example.movieapp.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.shared.BaseViewHolder
import com.example.shared.data.vos.MovieVO
import com.example.movieapp.delegates.MoviesDelegate
import com.example.shared.utils.IMAGE_URL
import kotlinx.android.synthetic.main.viewholder_movie.view.*

class MovieViewHolder(itemView: View, private val mDelegate: MoviesDelegate) : BaseViewHolder<MovieVO>(itemView) {

    var movieId : Int = 0

    init {
        itemView.setOnClickListener {
            mDelegate.onTapMovieItem(movieId)
        }
    }



    override fun bindData(data: MovieVO) {

        movieId = data.id
        Glide.with(itemView.context).load(IMAGE_URL+data.posterPath).into(itemView.ivMoviePhoto)
        itemView.tvMovieName.text = data.originalTitle
        itemView.tvPercentage.text = data.voteAverage.toString()

        itemView.btnFav.setOnClickListener {
            mDelegate.onTapFavMovie(data)
        }
    }
}