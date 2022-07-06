package com.example.shared.data.model

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.shared.data.vos.GenresVO
import com.example.shared.data.vos.MovieVO
import com.example.shared.utils.API_KEY
import com.example.shared.utils.EM_NO_INTERNET_CONNECTION
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MovieModelImpl : BaseModel(), MovieModel {

    @SuppressLint("CheckResult")
    override fun getMoviesFromApiAndSaveToDatabase(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mMovieApi.getPopularMovies(API_KEY)
            .map { it.results?.toList() ?: listOf() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                it.forEach { it.isPopular=true }
                mTheDB.movieDao().insertAllMovies(it)
            },{
                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
            })

        mMovieApi.getUpcomingMovies(API_KEY)
            .map { it.results?.toList() ?: listOf() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.forEach { it.isUpcoming=true }
                mTheDB.movieDao().insertAllMovies(it)
            },{
                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
            })

        mMovieApi.getGenresList(API_KEY)
            .map { it.genres?.toList() ?: listOf() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mTheDB.genresDao().insertGenresList(it)

            },{
                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
            })
    }

    override fun getPopularMovies(onError: (String) -> Unit): LiveData<List<MovieVO>> {
        return mTheDB.movieDao().getPopularMovies()
    }

    override fun getUpcomingMovies(onError: (String) -> Unit): LiveData<List<MovieVO>> {
        return mTheDB.movieDao().getUpcomingMovies()
    }

    @SuppressLint("CheckResult")
    override fun getMovieById(id: Int): LiveData<MovieVO> {
        mMovieApi.getMovieDetails(id, API_KEY)
            .subscribeOn(Schedulers.io())
            .subscribe({
                mTheDB.movieDao().insertMovie(it)
            },{
                Log.e("error", it.localizedMessage)
            })
        return mTheDB.movieDao().getMovieById(id)
    }

    override fun getGenresList(): LiveData<List<GenresVO>> {
        return mTheDB.genresDao().getGenresList()
    }

    override fun setFavouriteMovie(movie: MovieVO) {
        movie.isFavourite = true
        mTheDB.movieDao().insertMovie(movie)
    }
}