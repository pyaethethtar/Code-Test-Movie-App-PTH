package com.example.shared.data.model

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.example.shared.data.vos.GenresVO
import com.example.shared.data.vos.MovieVO
import com.example.shared.data.vos.PersonVO
import com.example.shared.network.responses.GetActorsListResponse
import com.example.shared.network.responses.GetMoviesResponse
import com.example.shared.utils.API_KEY
import com.example.shared.utils.EM_NO_INTERNET_CONNECTION
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MovieModelImpl : BaseModel(), MovieModel {

    override fun getMoviesFromApiAndSaveToDatabase(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mMovieApi.getPopularMovies(API_KEY).enqueue(object : Callback<GetMoviesResponse>{
            override fun onResponse(
                call: Call<GetMoviesResponse>,
                response: Response<GetMoviesResponse>
            ) {
               if (response.isSuccessful){
                   response.body()?.let {
                       val movies = it.results ?: listOf<MovieVO>()
                       movies.forEach { it.isPopular=true }
                       mTheDB.movieDao().insertAllMovies(movies)
                   }
               }else{
                   onError(response.errorBody().toString())
               }
            }

            override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                onError(t.localizedMessage?: EM_NO_INTERNET_CONNECTION)
            }

        })

        mMovieApi.getUpcomingMovies(API_KEY).enqueue(object : Callback<GetMoviesResponse>{
            override fun onResponse(
                call: Call<GetMoviesResponse>,
                response: Response<GetMoviesResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        val movies = it.results ?: listOf<MovieVO>()
                        movies.forEach { it.isUpcoming=true }
                        mTheDB.movieDao().insertAllMovies(movies)
                    }
                }else{
                    onError(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                onError(t.localizedMessage?: EM_NO_INTERNET_CONNECTION)
            }
        })

        mMovieApi.getActorsList(API_KEY).enqueue(object : Callback<GetActorsListResponse>{
            override fun onResponse(
                call: Call<GetActorsListResponse>,
                response: Response<GetActorsListResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        mTheDB.personDao().insertAllPeople(it.results?.toList()?: listOf())
                    }
                }
                else{
                    onError(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GetActorsListResponse>, t: Throwable) {
                onError(t.localizedMessage?: EM_NO_INTERNET_CONNECTION)
            }

        })
    }

//    @SuppressLint("CheckResult")
//    override fun getMoviesFromApiAndSaveToDatabase(
//        onSuccess: (String) -> Unit,
//        onError: (String) -> Unit
//    ) {
//        mMovieApi.getPopularMoviesObservable(API_KEY)
//            .map { it.results?.toList() ?: listOf() }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe ({
//                it.forEach { it.isPopular=true }
//                mTheDB.movieDao().insertAllMovies(it)
//                onSuccess("Success")
//            },{
//                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
//            })
//
//        mMovieApi.getUpcomingMovies(API_KEY)
//            .map { it.results?.toList() ?: listOf() }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                it.forEach { it.isUpcoming=true }
//                mTheDB.movieDao().insertAllMovies(it)
//            },{
//                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
//            })
//
//        mMovieApi.getGenresList(API_KEY)
//            .map { it.genres?.toList() ?: listOf() }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                mTheDB.genresDao().insertGenresList(it)
//
//            },{
//                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
//            })
//    }

    override fun getAllMovies(onError: (String) -> Unit): LiveData<List<MovieVO>> {
        return mTheDB.movieDao().getAllMovies()
    }

    @SuppressLint("CheckResult")
    override fun getMovieById(id: Int, onError: (String) -> Unit): LiveData<MovieVO> {
//        mMovieApi.getMovieDetails(id, API_KEY)
//            .subscribeOn(Schedulers.io())
//            .subscribe({
//                mTheDB.movieDao().insertMovie(it)
//            },{
//                Log.e("error", it.localizedMessage)
//            })
        mMovieApi.getMovieDetails(id, API_KEY).enqueue(object : Callback<MovieVO>{
            override fun onResponse(call: Call<MovieVO>, response: Response<MovieVO>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        it.isFavourite = mTheDB.movieDao().getFavouriteStatusById(id)
                        it.isUpcoming = mTheDB.movieDao().getUpcomingStatusById(id)
                        it.isPopular = mTheDB.movieDao().getPopularStatusById(id)
                        mTheDB.movieDao().insertMovie(it)
                    }
                }
                else{
                    onError(response.errorBody().toString())
                }
            }
            override fun onFailure(call: Call<MovieVO>, t: Throwable) {
                onError(EM_NO_INTERNET_CONNECTION)
            }
        })
        return mTheDB.movieDao().getMovieById(id)
    }

    override fun getActorsList(onError: (String) -> Unit): LiveData<List<PersonVO>> {
        return mTheDB.personDao().getActorList()
    }

    override fun getGenresList(): LiveData<List<GenresVO>> {
        return mTheDB.genresDao().getGenresList()
    }

    override fun setFavouriteMovie(movie: MovieVO) {
        if (movie.isFavourite){
            movie.isFavourite = false
        }
        else{
            movie.isFavourite = true
        }

        mTheDB.movieDao().insertMovie(movie)
    }
}