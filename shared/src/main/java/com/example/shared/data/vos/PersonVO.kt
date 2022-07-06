package com.example.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "people")
data class PersonVO (

    @PrimaryKey
    @SerializedName("id") var id : Int = 0,
    @SerializedName("name") var name : String = "",
    @SerializedName("profile_path") var profilePath : String = ""
)