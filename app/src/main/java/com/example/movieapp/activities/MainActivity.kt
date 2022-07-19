package com.example.movieapp.activities

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.R
import com.example.movieapp.viewpods.MovieTypesViewPod
import com.example.movieapp.viewpods.UpcomingMoviesViewPod
import com.example.shared.BaseActivity
import com.example.shared.data.vos.MovieVO
import com.example.movieapp.mvp.presenters.MainPresenter
import com.example.movieapp.mvp.presenters.MainPresenterImpl
import com.example.movieapp.mvp.views.MainView
import com.example.shared.data.vos.GenresVO
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), MainView {

    private lateinit var mPresenter : MainPresenter
    private lateinit var mMoviesViewPod : MovieTypesViewPod
    private lateinit var mUpcomingViewPod : UpcomingMoviesViewPod
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showWindowTransluentStatusBar()
        setUpPresenter()
        setUpViewPods()
        mPresenter.onUiReady(this)
    }

    private fun showWindowTransluentStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProvider(this).get(MainPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpViewPods(){
        mMoviesViewPod = vpMovieTypes as MovieTypesViewPod
        mMoviesViewPod.setDelegateAndRecyclerview(mPresenter)

        mUpcomingViewPod = vpUpcomingMovies as UpcomingMoviesViewPod
        mUpcomingViewPod.setDelegateAndRecyclerview(mPresenter)
    }

    override fun displayPopularMovies(popularMovies: List<MovieVO>) {
        mMoviesViewPod.displayPopularMovies(popularMovies)
    }

    override fun displayUpcomingMovies(upcomingMovies: List<MovieVO>) {
        mUpcomingViewPod.displayUpcomingMovies(upcomingMovies)
    }

    override fun displayGenresList(genres: List<GenresVO>) {
        mMoviesViewPod.displayGenresList(genres)
    }

    override fun navigateToMovieDetails(id: Int) {
        startActivity(DetailsActivity.newIntent(this, id))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}