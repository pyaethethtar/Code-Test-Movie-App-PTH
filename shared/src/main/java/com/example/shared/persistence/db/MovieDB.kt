package com.example.shared.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shared.data.vos.GenresVO
import com.example.shared.data.vos.MovieVO
import com.example.shared.data.vos.PersonVO
import com.example.shared.persistence.daos.GenresDao
import com.example.shared.persistence.daos.MovieDao
import com.example.shared.persistence.daos.PersonDao

@Database(entities = [MovieVO::class, GenresVO::class, PersonVO::class], version = 2, exportSchema = false)
abstract class MovieDB : RoomDatabase(){

    companion object{

        val DB_NAME = "Movie_DB"
        var dbInstance: MovieDB?=null

        fun getDBInstance(context: Context): MovieDB {
            when(dbInstance){
                null -> {
                    dbInstance = Room.databaseBuilder(context, MovieDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            val i= dbInstance
            return i!!
        }
    }

    abstract fun movieDao() : MovieDao
    abstract fun genresDao() : GenresDao
    abstract fun personDao() : PersonDao
}