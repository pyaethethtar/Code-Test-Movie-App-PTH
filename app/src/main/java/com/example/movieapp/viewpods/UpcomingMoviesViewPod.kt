package com.example.movieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapters.UpcomingMoviesAdapter
import com.example.shared.data.vos.MovieVO
import com.example.movieapp.delegates.MoviesDelegate
import kotlinx.android.synthetic.main.viewpod_upcoming_movies.view.*

class UpcomingMoviesViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var mDelegate : MoviesDelegate
    private lateinit var mAdapter : UpcomingMoviesAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setDelegateAndRecyclerview(delegate: MoviesDelegate){
        mDelegate = delegate

        val movieLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvUpcoming.layoutManager= movieLayoutManager

        mAdapter= UpcomingMoviesAdapter(mDelegate)
        rvUpcoming.adapter=mAdapter
    }

    fun displayUpcomingMovies(movies: List<MovieVO>) {
        mAdapter.setNewData(movies)
    }
}