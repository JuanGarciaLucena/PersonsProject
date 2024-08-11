package com.juanlucena.personsproject.data.repository

import com.juanlucena.personsproject.data.database.dao.PersonsDao
import com.juanlucena.personsproject.data.database.entity.PersonEntity
import com.juanlucena.personsproject.data.dtomodel.PersonDtoModel
import com.juanlucena.personsproject.data.network.PersonDataSource
import com.juanlucena.personsproject.domain.PersonModel
import com.juanlucena.personsproject.domain.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val personDataSource: PersonDataSource,
    private val personsDao: PersonsDao
): PersonRepository {

    override suspend fun getPersons(): Flow<PersonDtoModel> {
        return flow {
            emit(personDataSource.getPersonsFromApi())
        }
    }

    override suspend fun addPersonToFavorites(personModel: PersonModel) {
        personsDao.savePerson(personModel.toEntity())
    }

    override suspend fun getSavedPersons(): Flow<List<PersonEntity>> {
        return flow {
            emit(personsDao.getAllPersons())
        }
    }

    override suspend fun isPersonFavorite(email: String): Flow<Boolean> {
        return personsDao.isPersonFavorite(email)
    }

    override suspend fun deletePersonFromFavorites(person: PersonModel) {
        personsDao.deletePersonFromFavorites(person.toEntity())
    }
}