package com.example.rickmortyapi.repository

import android.util.Log
import com.example.rickmortyapi.api.RetrofitServiceLocations
import com.example.rickmortyapi.models.locations.LocationResult
import kotlinx.coroutines.delay

class LocationsRepository {
    suspend fun getData(page: Int): LocationResult {
        delay(1000)
        return RetrofitServiceLocations.searchRickAndMortyApiLocations.getLocations(page = page)
        Log.d("RESULTAT_LOCATIONS", "result - ${getData(page)}")
    }
}
