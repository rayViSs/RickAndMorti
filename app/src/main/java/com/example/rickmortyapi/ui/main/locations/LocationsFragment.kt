package com.example.rickmortyapi.ui.main.locations


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.rickmortyapi.R
import com.example.rickmortyapi.layout.LocationElement

class LocationsFragment : Fragment() {
    private val viewModel by viewModels<LocationsViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T = LocationsViewModel() as T
        }
    }

    private val pageData by lazy { LocationsPagingSource.pager(viewModel) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            LocationsListView()
        }
    }

    @Composable
    fun LocationsListView() {
        val items: LazyPagingItems<LocationElement> = pageData.flow.collectAsLazyPagingItems()

        LazyColumn(
            modifier = Modifier
                .padding(bottom = 30.dp)
        ) {
            items(items) {
                it?.let { LocationView(locationElement = it) } ?: Text(text = "Ooops")
            }

            items.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillParentMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    loadState.refresh is LoadState.Error -> {
                        item {
                            Text(
                                text = "Failed to load data!",
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        item {
                            Text(
                                text = "Failed to load data!",
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun LocationView(locationElement: LocationElement) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(resources.getColor(R.color.rm_grey_blue)))
                .padding(vertical = 8.dp, horizontal = 16.dp)

        ) {
            Text(
                text = locationElement.name,
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
            Text(
                text = locationElement.type,
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }
    }

    companion object {
        fun newInstance() = LocationsFragment()
    }
}



