package com.example.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsTypeConverter {

    @TypeConverter
    fun toString(genreIds : ArrayList<Int>) : String{
        return Gson().toJson(genreIds)
    }

    @TypeConverter
    fun toIdList(genreIdsJsonString: String) : ArrayList<Int>{
        val genreIdsType = object : TypeToken<ArrayList<Int>>(){}.type
        return Gson().fromJson(genreIdsJsonString, genreIdsType)
    }
}