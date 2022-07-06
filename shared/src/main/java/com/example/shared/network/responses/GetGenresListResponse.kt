package com.example.shared.network.responses

import com.example.shared.data.vos.GenresVO
import com.google.gson.annotations.SerializedName

data class GetGenresListResponse (

   @SerializedName("genres") val genres: ArrayList<GenresVO> ?= null
)