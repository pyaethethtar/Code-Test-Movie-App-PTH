package com.example.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shared.data.vos.PersonVO

@Dao
interface PersonDao {

    @Query("SELECT * FROM people")
    fun getActorList(): LiveData<List<PersonVO>>

    @Query("SELECT * FROM people WHERE id = :id")
    fun getActorById(id: Int): LiveData<PersonVO>

    @Insert
    fun insertAllPeople(people: List<PersonVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(person: PersonVO)


}