package com.example.rickmortyapi.models.locations

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "created") val created: String,
    @Json(name = "dimension") val dimension: String,
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "residents") val residents: List<String>,
    @Json(name = "type") val type: String,
    @Json(name = "url") val url: String
)