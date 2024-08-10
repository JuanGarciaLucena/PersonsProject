package com.juanlucena.personsproject.di

import com.juanlucena.personsproject.BuildConfig
import com.juanlucena.personsproject.data.network.PersonRetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesPersonApiClient(retrofit: Retrofit) : PersonRetrofitApi {
        return retrofit.create(PersonRetrofitApi::class.java)
    }
}