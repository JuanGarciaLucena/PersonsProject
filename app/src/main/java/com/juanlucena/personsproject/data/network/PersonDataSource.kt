package com.juanlucena.personsproject.data.network

import javax.inject.Inject

class PersonDataSource @Inject constructor(private val api: PersonRetrofitApi) {

    suspend fun getPersonsFromApi() = api.getPersons()
}