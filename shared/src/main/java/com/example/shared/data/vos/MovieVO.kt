package com.example.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shared.persistence.typeconverters.CountryVOTypeConverter
import com.example.shared.persistence.typeconverters.GenreIdsTypeConverter
import com.example.shared.persistence.typeconverters.GenreVOTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
@TypeConverters(GenreIdsTypeConverter::class, GenreVOTypeConverter::class, CountryVOTypeConverter::class)
data class MovieVO (

    @PrimaryKey
    @SerializedName("id")    var id: Int= 0,
    @SerializedName("popularity")var popularity: Double = 0.0,
    @SerializedName("vote_count") var voteCount: Int = 0,
    @SerializedName("video") var video: Boolean = false,
    @SerializedName("poster_path") var posterPath: String = "",
    @SerializedName("adult") var adult: Boolean = false,
    @SerializedName("backdrop_path") var backDropPath: String = "",
    @SerializedName("original_language") var originalLanguage:String = "",
    @SerializedName("original_title") var originalTitle : String = "",
    @SerializedName("genre_ids") var genreIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("title") var title: String = "",
    @SerializedName("vote_average") var voteAverage: Double = 0.0,
    @SerializedName("overview") var overview: String = "",
    @SerializedName("release_date") var releaseDate: String = "",
    @SerializedName("runtime") var runtime: Int ?= 0,
    @SerializedName("genres") var genres: ArrayList<GenresVO> = arrayListOf(),
    @SerializedName("production_countries") var productionCountries: ArrayList<CountryVO> = arrayListOf(),
    var isPopular : Boolean = false,
    var isUpcoming : Boolean = false,
    var isFavourite : Boolean = false

)