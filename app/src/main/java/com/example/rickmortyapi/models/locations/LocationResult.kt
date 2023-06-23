package com.example.rickmortyapi.models.locations

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationResult(
    @Json(name = "info") val info: Info,
    @Json(name = "results") val results: List<Result>
)