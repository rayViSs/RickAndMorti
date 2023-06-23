package com.example.rickmortyapi.ui.main.locations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmortyapi.layout.LocationElement
import com.example.rickmortyapi.repository.LocationsRepository

class LocationsPagingSource(viewModel: LocationsViewModel) : PagingSource<Int, LocationElement>() {
    private val repository = LocationsRepository()

    override fun getRefreshKey(state: PagingState<Int, LocationElement>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationElement> {
        val page = params.key ?: 1
        val response = repository.getData(page)
        val locations = response.results.map { location ->
            LocationElement(
                name = location.name,
                type = location.type
            )
        }

        return kotlin.runCatching {
            repository.getData(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = locations,
                    prevKey = null,
                    nextKey = if (it.results.isEmpty()) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }
    companion object {
        fun pager(viewModel: LocationsViewModel) = Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { LocationsPagingSource(viewModel) },
        )
    }
}

