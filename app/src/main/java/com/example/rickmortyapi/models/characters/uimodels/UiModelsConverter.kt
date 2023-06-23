package com.example.rickmortyapi.models.characters.uimodels

import com.example.rickmortyapi.models.characters.Location
import com.example.rickmortyapi.models.characters.Origin
import com.example.rickmortyapi.models.characters.Result

class UiModelsConverter {

    fun convert(result: Result): ResultUiModel {
        return ResultUiModel(
            result.created,
            result.episode,
            result.gender,
            result.id,
            result.image,
            convert(result.location),
            result.name,
            convert(result.origin),
            result.species,
            result.status,
            result.type,
            result.url
        )
    }

    private fun convert(location: Location): LocationUiModel {
        return LocationUiModel(
            location.name,
            location.url
        )
    }

    private fun convert(origin: Origin): OriginUiModel {
        return OriginUiModel(
            origin.name,
            origin.url
        )
    }
}