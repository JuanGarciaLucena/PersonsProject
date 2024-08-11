package com.juanlucena.personsproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.juanlucena.personsproject.data.database.dao.PersonsDao
import com.juanlucena.personsproject.data.database.entity.PersonEntity

@Database(entities = [PersonEntity::class], version = 1, exportSchema = false)
@TypeConverters(PersonConverter::class)
abstract class PersonsDb: RoomDatabase() {

    abstract fun getPersonsDao(): PersonsDao
}