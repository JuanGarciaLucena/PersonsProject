package com.juanlucena.personsproject.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.juanlucena.personsproject.data.database.entity.PersonEntity

@Dao
interface PersonsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePerson(personEntity: PersonEntity)
}