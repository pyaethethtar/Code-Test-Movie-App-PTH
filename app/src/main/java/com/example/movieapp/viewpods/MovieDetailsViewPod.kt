package com.example.movieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.example.movieapp.R
import com.example.shared.data.vos.MovieVO
import com.example.movieapp.mvp.presenters.DetailsPresenter
import kotlinx.android.synthetic.main.viewpod_movie_details.view.*
import kotlinx.android.synthetic.main.viewpod_movie_details.view.btnFav
import kotlinx.android.synthetic.main.viewpod_movie_details.view.tvMovieName
import kotlinx.android.synthetic.main.viewpod_movie_details.view.tvPercentage

class MovieDetailsViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private lateinit var mPresenter: DetailsPresenter
    private var mMovie = MovieVO()

    override fun onFinishInflate() {
        super.onFinishInflate()

        btnFav.setOnClickListener {
            mPresenter.onTapFavMovie(mMovie)
        }
    }

    fun setPresenter(presenter: DetailsPresenter){
        mPresenter = presenter
    }

    fun displayMovieDetails(movie : MovieVO){
        mMovie = movie

        tvMovieName.text = movie.originalTitle
        tvPercentage.text = movie.voteAverage.toString()
        tvDate.text = movie.releaseDate
        tvVotes.text = movie.voteCount.toString()+" VOTES"

        var genreList = ""
        movie.genres.forEach{
            genreList+="${it.name} "
        }
        tvTime.text = movie.runtime?.convertToHrAndMin() +"| "+genreList
        tvLanguage.text = movie.originalLanguage
        tvMovieDescription.text = movie.overview
        if (movie.isFavourite){
            btnFav.setBackgroundResource(R.drawable.ic_favorite_selected_24)
        }
        else{
            btnFav.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    fun Int.convertToHrAndMin() : String{
        val hr = this/60
        val min = this%60
        return "${hr}hr ${min}min "
    }
}