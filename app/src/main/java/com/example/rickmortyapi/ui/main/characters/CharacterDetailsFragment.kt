package com.example.rickmortyapi.ui.main.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import coil.compose.rememberImagePainter
import com.example.rickmortyapi.MainActivity
import com.example.rickmortyapi.R
import com.example.rickmortyapi.databinding.FragmentCharacterDetailsBinding
import com.example.rickmortyapi.models.characters.uimodels.ResultUiModel
import com.example.rickmortyapi.models.characters.uimodels.UiModelsConverter


class CharacterDetailsFragment(item: com.example.rickmortyapi.models.characters.Result?) :
    Fragment() {

    constructor() : this(null)

    companion object {
        private const val ITEM_ARG_KEY = "item"

        fun newInstance(item: com.example.rickmortyapi.models.characters.Result?) =
            CharacterDetailsFragment(item)
    }

    private var binding: FragmentCharacterDetailsBinding? = null
    private var details: ResultUiModel? = convertItem(item)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CharacterDetailsView(details)
        }

        if (savedInstanceState != null) details =
            savedInstanceState.getSerializable(ITEM_ARG_KEY) as ResultUiModel

        val actionBar = (activity as MainActivity?)!!.supportActionBar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(ITEM_ARG_KEY, details)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun convertItem(item: com.example.rickmortyapi.models.characters.Result?): ResultUiModel? {
        if (item == null) return null
        return UiModelsConverter().convert(item)
    }


    @Composable
    fun CharacterDetailsView(details: ResultUiModel?) {
        if (details != null) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .background(color = Color(resources.getColor(R.color.rm_grey_blue)))
            ) {

//            GlideImageWithPreview(
//                data = details.image,
//                Modifier
//                    .height(220.dp)
//                    .fillMaxWidth(),
//                contentScale = ContentScale.FillBounds
//            )

                Image(
                    painter = rememberImagePainter(details.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                )

                Text(
                    text = details.name,
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    modifier = Modifier
                        .padding(
                            vertical = 16.dp,
                            horizontal = 16.dp
                        )
                )


                Text(
                    text = "Status: ${details.status}",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .padding(
                            horizontal = 16.dp
                        )
                )

                Text(
                    text = "Species: ${details.species} • Gender: ${details.gender}",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .padding(
                            horizontal = 16.dp
                        )
                )

                Text(
                    text = "Last known location: ${details.location.name}",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                        .padding(
                            horizontal = 16.dp
                        )
                )

                Text(
                    text = "First seen in: ${details.origin.name}",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                        .padding(
                            horizontal = 16.dp
                        )
                )

                Text(
                    text = "Episodes:",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                        .padding(
                            horizontal = 16.dp
                        )
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(
                            horizontal = 16.dp
                        )
                ) {
                    items(details.episode) { episode ->
                        Text(
                            text = episode,
                            color = Color.White,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                }
            }
        }
    }


}

