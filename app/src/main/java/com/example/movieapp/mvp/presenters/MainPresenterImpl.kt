package com.example.movieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.AbstractBasePresenter
import com.example.shared.data.model.MovieModel
import com.example.shared.data.model.MovieModelImpl
import com.example.shared.data.vos.MovieVO
import com.example.movieapp.mvp.views.MainView

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {

    private val mMovieModel: MovieModel = MovieModelImpl

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        //mView?.displayLoading()
        loadMoviesFromApi(lifecycleOwner)
        requestPopularMovies(lifecycleOwner)
        requestUpcomingMovies(lifecycleOwner)
    }

    override fun onTapMovieItem(id: Int) {
        mView?.navigateToMovieDetails(id)
    }

    override fun onTapFavMovie(movie: MovieVO) {
        mMovieModel.setFavouriteMovie(movie)
    }

    private fun loadMoviesFromApi(lifecycleOwner: LifecycleOwner){
        mMovieModel.getMoviesFromApiAndSaveToDatabase(onSuccess = {}, onError = {})
    }

    private fun requestPopularMovies(lifecycleOwner: LifecycleOwner){
        mMovieModel.getPopularMovies(
            onError = { mView?.displayError(it)}
        ).observe(lifecycleOwner, Observer {
            if (it.isNotEmpty()){
//                mView?.hideLoading()
                mView?.displayPopularMovies(it)
            }

        })
    }

    private fun requestUpcomingMovies(lifecycleOwner: LifecycleOwner) {
        mMovieModel.getUpcomingMovies(
            onError = { mView?.displayError(it)}
        ).observe(lifecycleOwner, Observer {
            if (it.isNotEmpty()){
//                mView?.hideLoading()
                mView?.displayUpcomingMovies(it)
            }

        })
    }


}