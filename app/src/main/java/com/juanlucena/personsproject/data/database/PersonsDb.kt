package com.juanlucena.personsproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juanlucena.personsproject.data.database.dao.PersonsDao
import com.juanlucena.personsproject.data.database.entity.PersonEntity

@Database(entities = [PersonEntity::class], version = 1, exportSchema = false)
abstract class PersonsDb: RoomDatabase() {

    abstract fun getPersonsDao(): PersonsDao
}