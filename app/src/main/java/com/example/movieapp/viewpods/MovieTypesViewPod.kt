package com.example.movieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapters.MoviesAdapter
import com.example.shared.data.vos.MovieVO
import com.example.movieapp.delegates.MoviesDelegate
import kotlinx.android.synthetic.main.viewpod_movie_types.view.*


class MovieTypesViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private lateinit var mDelegate : MoviesDelegate
    private lateinit var mAdapter : MoviesAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setDelegateAndRecyclerview(delegate: MoviesDelegate){
        mDelegate = delegate

        val movieLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvRecommend.layoutManager= movieLayoutManager

        mAdapter= MoviesAdapter(mDelegate)
        rvRecommend.adapter=mAdapter
    }

    fun displayPopularMovies(movies: List<MovieVO>) {
        mAdapter.setNewData(movies)
    }


}