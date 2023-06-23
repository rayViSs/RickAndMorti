package com.example.rickmortyapi.repository

import android.util.Log
import com.example.rickmortyapi.api.RetrofitServiceCharacters
import com.example.rickmortyapi.models.characters.CharactersList
import kotlinx.coroutines.delay

class CharactersRepository {
    suspend fun getData(page: Int): CharactersList {
        delay(1000)
        return RetrofitServiceCharacters.searchRickAndMortyApiCharacters.getCharacters(page = page)
        Log.d("RESULTAT_CHARACTERS", "result - ${getData(page)}")
    }
}