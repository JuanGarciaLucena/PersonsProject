package com.juanlucena.personsproject.di

import android.content.Context
import androidx.room.Room
import com.juanlucena.personsproject.data.database.PersonsDb
import com.juanlucena.personsproject.utils.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, PersonsDb::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideBeerDao(db: PersonsDb) = db.getPersonsDao()
}