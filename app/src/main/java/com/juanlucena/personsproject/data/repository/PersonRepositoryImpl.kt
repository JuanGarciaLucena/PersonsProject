package com.juanlucena.personsproject.data.repository

import com.juanlucena.personsproject.data.dtomodel.PersonDtoModel
import com.juanlucena.personsproject.data.network.PersonDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val personDataSource: PersonDataSource
): PersonRepository {

    override suspend fun getPersons(): Flow<PersonDtoModel> {
        return flow {
            emit(personDataSource.getPersonsFromApi())
        }
    }
}