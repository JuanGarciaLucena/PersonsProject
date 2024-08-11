package com.juanlucena.personsproject.data.repository

import com.juanlucena.personsproject.data.database.entity.PersonEntity
import com.juanlucena.personsproject.data.dtomodel.PersonDtoModel
import com.juanlucena.personsproject.domain.PersonModel
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun getPersons(): Flow<PersonDtoModel>
    suspend fun addPersonToFavorites(personModel: PersonModel)
    suspend fun getSavedPersons(): Flow<List<PersonEntity>>
    suspend fun isPersonFavorite(email: String): Flow<Boolean>
    suspend fun deletePersonFromFavorites(person: PersonModel)
}