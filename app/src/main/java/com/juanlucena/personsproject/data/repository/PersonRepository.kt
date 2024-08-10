package com.juanlucena.personsproject.data.repository

import com.juanlucena.personsproject.data.dtomodel.PersonDtoModel
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun getPersons(): Flow<PersonDtoModel>
}