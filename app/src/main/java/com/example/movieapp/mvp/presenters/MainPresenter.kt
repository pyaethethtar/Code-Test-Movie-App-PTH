package com.example.movieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.BasePresenter
import com.example.movieapp.delegates.MoviesDelegate
import com.example.movieapp.mvp.views.MainView

interface MainPresenter : BasePresenter<MainView>, MoviesDelegate {

    fun onUiReady(lifecycleOwner: LifecycleOwner)

}