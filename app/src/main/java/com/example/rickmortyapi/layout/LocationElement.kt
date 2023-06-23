package com.example.rickmortyapi.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class LocationElement(val name: String, val type: String)

@Composable
fun LocationListItem(location: LocationElement) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = location.name,
            style = MaterialTheme.typography.h5
        )
        Text(
            text = location.type,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview
@Composable
fun LocationPreview() = LocationListItem(
    location = LocationElement(
        name = "Earth",
        type = "Planet"
    )
)
