package com.example.movieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.BasePresenter
import com.example.shared.data.vos.MovieVO
import com.example.movieapp.mvp.views.DetailsView

interface DetailsPresenter : BasePresenter<DetailsView>{

    fun onDetailsUiReady(lifecycleOwner: LifecycleOwner, id: Int)
    fun onTapBack()
    fun onTapFavMovie(movie: MovieVO)
}