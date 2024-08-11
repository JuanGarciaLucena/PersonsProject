package com.juanlucena.personsproject.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juanlucena.personsproject.data.database.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePerson(personEntity: PersonEntity)

    @Query("SELECT * FROM persons_table")
    suspend fun getAllPersons(): List<PersonEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM persons_table WHERE email = :email)")
    fun isPersonFavorite(email: String): Flow<Boolean>

    @Delete
    suspend fun deletePersonFromFavorites(person: PersonEntity)
}