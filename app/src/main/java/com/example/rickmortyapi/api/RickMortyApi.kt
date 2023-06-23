package com.example.rickmortyapi.api

import com.example.rickmortyapi.models.characters.CharactersList
import com.example.rickmortyapi.models.locations.LocationResult
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://rickandmortyapi.com"

//CHARACTERS API
interface RickMortyApiCharacters {
    @GET("/api/character")
    suspend fun getCharacters(@Query("page")page: Int): CharactersList
}

object RetrofitServiceCharacters{
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val searchRickAndMortyApiCharacters: RickMortyApiCharacters = retrofit.create(RickMortyApiCharacters::class.java)
}

//LOCATIONS API
interface RickMortyApiLocations {
    @GET("/api/location")
    suspend fun getLocations(@Query("page")page: Int): LocationResult
}

object RetrofitServiceLocations{
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val searchRickAndMortyApiLocations: RickMortyApiLocations = retrofit.create(RickMortyApiLocations::class.java)
}


