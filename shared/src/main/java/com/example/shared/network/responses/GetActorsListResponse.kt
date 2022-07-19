package com.example.shared.network.responses

import com.example.shared.data.vos.MovieVO
import com.example.shared.data.vos.PersonVO
import com.google.gson.annotations.SerializedName

data class GetActorsListResponse (
    @SerializedName("page") val page: Int = 0,
    @SerializedName("results") val results: ArrayList<PersonVO> ?= null
)