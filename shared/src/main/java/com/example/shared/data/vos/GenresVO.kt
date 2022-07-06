package com.example.shared.data.vos


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genres")
data class GenresVO(

    @PrimaryKey
    @SerializedName("id")    var id: Int = 0,
    @SerializedName("name")     var name: String = ""
)