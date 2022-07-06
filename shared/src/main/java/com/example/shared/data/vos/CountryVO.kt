package com.example.shared.data.vos


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class CountryVO(

    @PrimaryKey
    @SerializedName("iso_3166_1") var iso : String = "",
    @SerializedName("name") var name : String = ""
)