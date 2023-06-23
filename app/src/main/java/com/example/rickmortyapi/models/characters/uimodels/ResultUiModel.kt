package com.example.rickmortyapi.models.characters.uimodels

import java.io.Serializable

data class ResultUiModel(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationUiModel,
    val name: String,
    val origin: OriginUiModel,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) : Serializable

