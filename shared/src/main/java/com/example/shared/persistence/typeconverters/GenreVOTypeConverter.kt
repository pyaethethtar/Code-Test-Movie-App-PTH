package com.example.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.shared.data.vos.GenresVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreVOTypeConverter {

    @TypeConverter
    fun toString(genres : ArrayList<GenresVO>) : String{
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toArrayList(genresJsonString: String) : ArrayList<GenresVO>{
        val genresType = object : TypeToken<ArrayList<GenresVO>>(){}.type
        return Gson().fromJson(genresJsonString, genresType)
    }
}