package com.example.movieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.AbstractBasePresenter
import com.example.shared.data.model.MovieModel
import com.example.shared.data.model.MovieModelImpl
import com.example.shared.data.vos.MovieVO
import com.example.movieapp.mvp.views.DetailsView

class DetailsPresenterImpl : DetailsPresenter, AbstractBasePresenter<DetailsView>() {

    private val  mMovieModel : MovieModel = MovieModelImpl

    override fun onDetailsUiReady(lifecycleOwner: LifecycleOwner, id: Int) {
        requestMovieDetails(lifecycleOwner, id)
    }

    override fun onTapBack() {
        mView?.goBackToMain()
    }

    override fun onTapFavMovie(movie: MovieVO) {
        mMovieModel.setFavouriteMovie(movie)
    }

    private fun requestMovieDetails(lifecycleOwner: LifecycleOwner, id: Int){

        mMovieModel.getMovieById(id)
            .observe(lifecycleOwner, Observer {
                if (it!=null){
                    mView?.displayMovieDetails(it)
                }
            })
    }

}