package com.example.movieapp

import android.app.Application
import com.example.shared.data.model.MovieModelImpl

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MovieModelImpl.initDatabase(applicationContext)
    }
}