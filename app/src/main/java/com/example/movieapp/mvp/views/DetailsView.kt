package com.example.movieapp.mvp.views

import com.example.shared.BaseView
import com.example.shared.data.vos.MovieVO
import com.example.shared.data.vos.PersonVO

interface DetailsView : BaseView {

    fun displayMovieDetails(details: MovieVO)
    fun goBackToMain()
}