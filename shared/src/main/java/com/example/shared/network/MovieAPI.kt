package com.example.shared.network


import com.example.shared.network.responses.*
import com.example.shared.data.vos.MovieVO
import com.example.shared.utils.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET(GET_POPULAR_MOVIES)
    fun getPopularMovies(@Query(API_KEY_PARAM) apiKey: String): Call<GetMoviesResponse>

    @GET(GET_UPCOMING_MOVIES)
    fun getUpcomingMovies(@Query(API_KEY_PARAM)apiKey: String): Call<GetMoviesResponse>

    @GET(GET_POPULAR_ACTORS)
    fun getActorsList(@Query(API_KEY_PARAM)apiKey: String): Call<GetActorsListResponse>

    @GET("$GET_MOVIE_DETAILS/{MOVIE_ID}")
    fun getMovieDetails(@Path("MOVIE_ID") movieId: Int, @Query(API_KEY_PARAM) apiKey: String): Call<MovieVO>

    @GET(GET_POPULAR_MOVIES)
    fun getPopularMoviesObservable(@Query(API_KEY_PARAM) apiKey: String): Observable<GetMoviesResponse>

    @GET(GET_UPCOMING_MOVIES)
    fun getUpcomingMoviesObservable(@Query(API_KEY_PARAM)apiKey: String): Observable<GetMoviesResponse>

    @GET(GET_GENRES_LIST)
    fun getGenresListObservable(@Query(API_KEY_PARAM) apiKey: String) : Observable<GetGenresListResponse>

    @GET("$GET_MOVIE_DETAILS/{MOVIE_ID}")
    fun getMovieDetailsObservable(@Path("MOVIE_ID") movieId: Int, @Query(API_KEY_PARAM) apiKey: String): Observable<MovieVO>

}