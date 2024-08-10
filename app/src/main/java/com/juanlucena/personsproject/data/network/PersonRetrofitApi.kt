package com.juanlucena.personsproject.data.network

import com.juanlucena.personsproject.data.dtomodel.PersonDtoModel
import retrofit2.http.GET

interface PersonRetrofitApi {

    @GET("?results=50")
    suspend fun getPersons(): PersonDtoModel
}