package com.example.shared.network.responses

import com.example.shared.data.vos.MovieVO
import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(

    @SerializedName("page") val page: Int = 0,
    @SerializedName("total_results") val totalResults : Int = 0,
    @SerializedName("total_pages") val totalPages: Int = 0,
    @SerializedName("results") val results: ArrayList<MovieVO> ?= null
)