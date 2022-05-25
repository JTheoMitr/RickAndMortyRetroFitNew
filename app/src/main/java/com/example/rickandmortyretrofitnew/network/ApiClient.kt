package com.example.rickandmortyretrofitnew.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    // lets get our BASE_URL
    private val BASE_URL = "https://rickandmortyapi.com/api/"

    // create variable for the moshi builder
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    // create an instance of RetroFit
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

// API Service Interface

interface ApiService {
    // get all characters
    @GET("character")
    suspend fun fetchCharacters(@Query("page") page: String)
    : CharacterResponse

    // NOW MAKE NEW CALLS FOR EPISODES, PLANETS, ETC
}